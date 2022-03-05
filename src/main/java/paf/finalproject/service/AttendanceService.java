package paf.finalproject.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import paf.finalproject.AttendanceRepo;
import paf.finalproject.models.Staff;
import paf.finalproject.models.timeSheet;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service
public class AttendanceService {
    @Autowired AttendanceRepo repo;

    private SecretKey signKey;

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
    public void clockin(String staffid){
        repo.clockin(staffid);
    }
    public void clockout(String staffid){
        repo.clockout(staffid);
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

}
