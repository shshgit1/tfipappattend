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
    public void addTimeLogFromDatabase(SqlRowSet srs,timeSheet tSheet){
        
        tSheet.setStaff_id(srs.getString("staff_id"));
        tSheet.setName(srs.getString("name"));
        tSheet.setDept(srs.getString("dept"));
        tSheet.setDate(srs.getString("Date"));
        tSheet.setClock_in(srs.getString("clock_in"));
        tSheet.setClock_out(srs.getString("clock_out"));
    }

    public JsonObject toJson(){
        return Json.createObjectBuilder().add("name", name).add("staffid", staff_id)
        .add("department", dept).add("Date", date).add("clock_in", clock_in)
        .add("clock_out",clock_out).build();
    }

}
