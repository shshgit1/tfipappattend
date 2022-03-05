package paf.finalproject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import paf.finalproject.models.Staff;
import paf.finalproject.models.adminuser;
import paf.finalproject.models.timeSheet;

@Repository
public class AttendanceRepo {

@Autowired
private JdbcTemplate template;


public void addStaff(Staff newSt){
    String name=newSt.getName();
    String dept=newSt.getDept();
    String staffid=newSt.getStaff_id();
    template.update
    ("insert into staff(staff_id,name,dept) values('"+staffid+"','"+name+"','"+dept+ "')");
}

public void clockin(String staffid){
    String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
    String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    template.update
    ("insert into timelog(staff_id, Date, clock_in) values ('"+staffid+"','"+todayDate+"','"+timeStamp+"')");

}

public void clockout(String staffid){
    String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
    String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    
    template.update
    ("update timelog set clock_out = '"+timeStamp+"' where Date = '"+todayDate+"' and staff_id='"+staffid+"'");
}

public List<Staff> showallStaff(){
    List<Staff> allstaff=new LinkedList<>();
    SqlRowSet srs=template.queryForRowSet("select * from staff");
    while (srs.next()){
        Staff newStaffToShow=new Staff();
        newStaffToShow.addStaffFromDatabase(srs,newStaffToShow);
        allstaff.add(newStaffToShow);
    }
    return allstaff;
}//end showallstaff

public List<timeSheet> genTimesheet(String staffid){
    List<timeSheet> ts=new LinkedList<>();
    SqlRowSet srs=template.queryForRowSet
    ("select * from staff left join timelog on staff.staff_id=timelog.staff_id  where staff.staff_id='"+staffid+"'");
    
    while (srs.next()){
        timeSheet tsheet=new timeSheet();
        tsheet.addTimeLogFromDatabase(srs,tsheet);
        ts.add(tsheet);
    }
    return ts;
}

public List<timeSheet> filterTimesheet(String staffid, String startDate, String endDate){
    List<timeSheet> ts=new LinkedList<>();
    SqlRowSet srs=template.queryForRowSet
    ("select * from staff left join timelog on staff.staff_id=timelog.staff_id where staff.staff_id='"+staffid+"' and Date between '"+startDate+"' and '"+endDate+"'");
    
    while (srs.next()){
        timeSheet tsheet=new timeSheet();
        tsheet.addTimeLogFromDatabase(srs,tsheet);
        ts.add(tsheet);
    }
    return ts;
}

public void removeStaffFromDb(String staffid){
    template.update("SET FOREIGN_KEY_CHECKS=0");
    template.update("delete from staff where staff.staff_id ='"+staffid+"'");
}

//chuk code below
public Optional<adminuser> findUserByName(String username) {
    final SqlRowSet rs = template.queryForRowSet("select * from user where username = ?", username);
    if (rs.next())
        return Optional.of(adminuser.populate(rs));
    return Optional.empty();
}

public boolean validateUser(String username, String password) {
    SqlRowSet rs = template.queryForRowSet("select count(*) as user_count from adminusers where username = ? and BINARY password like ?", username, password);
    System.out.println("ab> "+rs);
    if (!rs.next())
        return false;

    return rs.getInt("user_count") > 0;
}

}