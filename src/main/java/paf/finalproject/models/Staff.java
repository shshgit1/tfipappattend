package paf.finalproject.models;

import java.io.ByteArrayInputStream;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Staff {
    
    private String name;
    private String staff_id;
    private String dept;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getStaff_id(){
        return staff_id;
    }

    public void setStaff_id(String staff_id){
        this.staff_id=staff_id;
    }


    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Staff create(String staffasjsonstring){
        JsonReader r=Json.createReader(new ByteArrayInputStream(staffasjsonstring.getBytes()));

        JsonObject o=r.readObject();

        Staff newstaff=new Staff();

        newstaff.name=o.getString("name");
        newstaff.staff_id=o.getString("staffid");
        newstaff.dept=o.getString("department");

        //these must match the staff.ts fields

        return newstaff;

    }

    public void addStaffFromDatabase(SqlRowSet srs,Staff staff){
        
        staff.setStaff_id(srs.getString("staff_id"));
        staff.setName(srs.getString("name"));
        staff.setDept(srs.getString("dept"));
    }

    public JsonObject toJson(){
        return Json.createObjectBuilder().add("name", name).add("staffid", staff_id)
        .add("department", dept).build();
    }

}
