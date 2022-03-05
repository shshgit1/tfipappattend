import { Component, OnInit } from '@angular/core';
import { AttendService } from '../attend.service';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {
  groupedForm:FormGroup;
  StaffIdInp=new FormControl();
  RemarksInp=new FormControl();


  constructor(private fb:FormBuilder, private attsvc:AttendService) {
    this.groupedForm=this.fb.group(
      {
      StaffIdinput:this.StaffIdInp,
      Remarks:this.RemarksInp
      }
    )
  }

  ngOnInit(): void {
  }

  clockin(){
    this.attsvc.clockin(this.groupedForm.value.StaffIdinput)
    .then((response)=>
    {
      if(response=='OK')
    {
      alert("Succesfully clocked in");
    }
    else
    {
      alert("Staff ID does not exist.")
    }
  }
  )
    .catch(err=>alert("Staff ID cannot be empty"))

    this.groupedForm.reset();
  }
  clockout(){
    this.attsvc.clockout(this.groupedForm.value.StaffIdinput) .then((response)=>
    {
      if(response=='OK')
    {
      alert("Succesfully clocked out");
    }
    else
    {
      alert("Staff ID does not exist.")
    }
  }
  )
    .catch(err=>alert("Staff ID cannot be empty"))
    this.groupedForm.reset();
  }

}
