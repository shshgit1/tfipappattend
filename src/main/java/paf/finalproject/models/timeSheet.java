package paf.finalproject.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class timeSheet {
    private String name;
    private String staff_id;
    private String dept;
    private String date;
    private String clock_in;
    private String clock_out;
    private String clock_in_location;
    private String clock_out_location;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staffid) {
        this.staff_id=staffid;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept){
        this.dept=dept;
    }


    public String getDate() {
        return date;
    }

    

    public void setDate(String date) {
        this.date = date;
    }


    public String getClock_in() {
        return clock_in;
    }


    public void setClock_in(String clock_in) {
        this.clock_in = clock_in;
    }


    public String getClock_out() {
        return clock_out;
    }


    public void setClock_out(String clock_out) {
        this.clock_out = clock_out;
    }

    public String getClock_in_location() {
        return clock_in_location;
    }


    public void setClock_in_location(String clock_in_location) {
        this.clock_in_location = clock_in_location;
    }


    public String getClock_out_location() {
        return clock_out_location;
    }

    public void setClock_out_location(String clock_out_location) {
        this.clock_out_location = clock_out_location;
    }
    public void addTimeLogFromDatabase(SqlRowSet srs,timeSheet tSheet){
        
        tSheet.setStaff_id(srs.getString("staff_id"));
        tSheet.setName(srs.getString("name"));
        tSheet.setDept(srs.getString("dept"));
        tSheet.setDate(srs.getString("Date"));
        tSheet.setClock_in(srs.getString("clock_in"));
        tSheet.setClock_out(srs.getString("clock_out"));
        tSheet.setClock_in_location(srs.getString("clock_in_loc"));
        tSheet.setClock_out_location(srs.getString("clock_out_loc"));
    }

    public JsonObject toJson(){
        return Json.createObjectBuilder().add("name", name).add("staffid", staff_id)
        .add("department", dept).add("Date", date).add("clock_in", clock_in)
        .add("clock_out",clock_out).add("clock_in_location", clock_in_location).add("clock_out_location", clock_out_location)
        .build();
    }



   

}
