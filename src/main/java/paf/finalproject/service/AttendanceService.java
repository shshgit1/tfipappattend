package paf.finalproject.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import paf.finalproject.AttendanceRepo;
import paf.finalproject.models.Staff;
import paf.finalproject.models.timeSheet;


@Service
public class AttendanceService {
    @Autowired AttendanceRepo repo;

    private SecretKey signKey;

	String urlinput="https://www.alphavantage.co/query";
	
	private Logger log=Logger.getLogger("from the service class");

    @PostConstruct
	public void init() {
		String passphrase = "c8f46a13-f478-431c-9dc8-07db63be3cbe";

		try {
			signKey = Keys.hmacShaKeyFor(passphrase.getBytes("UTF-8"));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new IllegalArgumentException("Creating HMAC Sign key", ex);
		}
	}

    public void addNewStaff(Staff newSt){
        repo.addStaff(newSt);
    }
    public void clockin(String staffid, String lat, String lng){
        repo.clockin(staffid,lat,lng);
    }
    public void clockout(String staffid,String lat, String lng){
        repo.clockout(staffid,lat,lng);
    }
    public List<Staff> showAllStaff(){
        return repo.showallStaff();
    }
    public List<timeSheet> genTimesheet(String staffid){
        return repo.genTimesheet(staffid);
    }
    public void removestaff(String staffid){
        repo.removeStaffFromDb(staffid);
    }
    public List<timeSheet> filterTimesheet(String staffid,String start, String end){
        return repo.filterTimesheet(staffid, start, end);

    }
    //chuk below
    public Optional<JsonObject> authenticate(String username, String password){

		if (!repo.validateUser(username, password))
			return Optional.empty();

		String token = Jwts.builder()
			.setSubject(username)
			.signWith(signKey)
			.compact();

		return Optional.of(Json.createObjectBuilder()
			.add("subject", username)
			.add("token", token)
			.build());
	}
    public boolean validate(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(signKey).build()
				.parseClaimsJws(token);
			return true;
		} catch (JwtException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public String getInterestRate() throws IOException{
		
        String url=UriComponentsBuilder.
        fromUriString(urlinput).
        queryParam("function", "TREASURY_YIELD").
        queryParam("interval", "DAILY").
		queryParam("apikey", "QXSMR1LEDWTJS0YR").
        toUriString(); 
		
		
        RequestEntity req=RequestEntity.get(url).build();
        RestTemplate temple=new RestTemplate();
        ResponseEntity<String> resp=temple.exchange(req, String.class);

		String body=resp.getBody();

        try(InputStream is=new ByteArrayInputStream(body.getBytes()))
        {

        JsonReader reader= Json.createReader(is);

        JsonObject result=reader.readObject();

        JsonArray jarray=result.getJsonArray("data");
        
        String getcurrentyield=jarray.getJsonObject(0).getString("value");

        return getcurrentyield;   
        }//end try
        catch(Exception e){
            System.out.println("catch fail");
            return "fail";
        }//end catch

        }
    
}
