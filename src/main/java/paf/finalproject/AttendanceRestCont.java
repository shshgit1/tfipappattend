package paf.finalproject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import paf.finalproject.models.ContactSupport;
import paf.finalproject.models.Staff;
import paf.finalproject.models.timeSheet;
import paf.finalproject.service.AttendanceService;
import paf.finalproject.service.EmailService;


@RestController
@RequestMapping(path="/attendance")
public class AttendanceRestCont {
    @Autowired
    private AttendanceService attsvc;

    @Autowired
    private EmailService emailService;

    
    @PostMapping(path="add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> addStaff(@RequestBody String payload)
    {
        Staff S=new Staff();

        S=S.create(payload);

        attsvc.addNewStaff(S);
        
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(path="clockin",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> clockinEntry(@RequestBody String payload){
        System.out.println(payload);
        
        JsonReader r=Json.createReader(new ByteArrayInputStream(payload.getBytes()));
        JsonObject o=r.readObject();
        String lat=o.getJsonNumber("lat").toString();
        String lng=o.getJsonNumber("lat").toString();
        try{
            
            attsvc.clockin(o.getString("staffid"),lat,lng);
            
            return ResponseEntity.ok(HttpStatus.OK);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        } 
    } 

    @PostMapping(path="clockout")
    public ResponseEntity<HttpStatus> clockoutEntry(@RequestBody String payload){
        
        JsonReader r=Json.createReader(new ByteArrayInputStream(payload.getBytes()));
        JsonObject o=r.readObject();

        String lat=o.getJsonNumber("lat").toString();
        String lng=o.getJsonNumber("lat").toString();

        try{
        attsvc.clockout(o.getString("staffid"),lat,lng);
        return ResponseEntity.ok(HttpStatus.OK);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(path="listall")
    public ResponseEntity<String> ShowAllStaff(){
        List<Staff> showallStaff= attsvc.showAllStaff();
        JsonArrayBuilder buildanarray = Json.createArrayBuilder();

        for (int x=0;x<showallStaff.size();x++){
            buildanarray.add(showallStaff.get(x).toJson());
        }

        return ResponseEntity.ok(buildanarray.build().toString());
    }

    @GetMapping(path="timesheet/{staffid}")
    public ResponseEntity<String> generateTimesheet(@PathVariable String staffid){
        List<timeSheet> timesheet= attsvc.genTimesheet(staffid);
        JsonArrayBuilder buildanarray = Json.createArrayBuilder();

        for (int x=0;x<timesheet.size();x++){
            buildanarray.add(timesheet.get(x).toJson());
        }

        return ResponseEntity.ok(buildanarray.build().toString());
    }

    @GetMapping(path="timesheetfilter/{staffid}")
    public ResponseEntity<String> filterTimesheet
    (@PathVariable String staffid,@RequestParam String start,@RequestParam String end){
        List<timeSheet> timesheet= attsvc.filterTimesheet(staffid,start,end);
        JsonArrayBuilder buildanarray = Json.createArrayBuilder();
        
        for (int x=0;x<timesheet.size();x++){
            buildanarray.add(timesheet.get(x).toJson());
        }

        return ResponseEntity.ok(buildanarray.build().toString());
    }

    @PostMapping(path="remove")
    public ResponseEntity<HttpStatus> removeStaff(@RequestBody String staffid){
        
        attsvc.removestaff(staffid);
        return ResponseEntity.ok(HttpStatus.OK);  
    }

    @PostMapping(path="auth")
	public ResponseEntity<String> authenticateUser(@RequestBody String loginRequest) {
        
    JsonReader r=Json.createReader(new ByteArrayInputStream(loginRequest.getBytes()));

        JsonObject o=r.readObject();

        Optional<JsonObject> opt  = attsvc.authenticate(o.getString("username"), o.getString("password"));
        if (opt.isPresent()){
				return ResponseEntity.ok(opt.get().toString());
                }
    else return ResponseEntity.ok("fail");
    }

    @PostMapping(path="contactus",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactSupport> support(@RequestBody ContactSupport support){
        System.out.println("from pospo>"+support.getEnquiry());
        try {
            
        emailService.sendEmail(support);
        return ResponseEntity.ok(support);
        } catch( MailException e){
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="getInterest")
    public String getInterestRate() throws IOException{  

        return attsvc.getInterestRate();
    }

}

