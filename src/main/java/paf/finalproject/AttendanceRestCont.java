package paf.finalproject;

import java.io.ByteArrayInputStream;
import java.io.Console;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import paf.finalproject.models.Staff;
import paf.finalproject.models.timeSheet;
import paf.finalproject.service.AttendanceService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path="/attendance")
public class AttendanceRestCont {
    @Autowired
    private AttendanceService attsvc;

    
    @PostMapping(path="add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> addStaff(@RequestBody String payload)
    {
        Staff S=new Staff();

        S=S.create(payload);

        attsvc.addNewStaff(S);
        
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(path="clockin")
    public ResponseEntity<HttpStatus> clockinEntry(@RequestBody String payload){
        try{

        attsvc.clockin(payload);
        return ResponseEntity.ok(HttpStatus.OK);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    } 

    @PostMapping(path="clockout")
    public ResponseEntity<HttpStatus> clockoutEntry(@RequestBody String payload){
        try{
        attsvc.clockout(payload);
        return ResponseEntity.ok(HttpStatus.OK);
        }
        catch(Exception e){
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
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

}

