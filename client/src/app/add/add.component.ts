import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { AttendService } from '../attend.service';
import { staff } from '../staff';


@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  groupedForm:FormGroup;
  NameInput=new FormControl();
  DeptInput=new FormControl();
  StaffIdInput=new FormControl();

  showname:string='';
  showdept:string='';
  showstaffid:string='';

  constructor(private fb:FormBuilder, private svc:AttendService) {
    this.groupedForm=this.fb.group(
      {
        Name:this.NameInput,
        Dept:this.DeptInput,
        StaffID:this.StaffIdInput
      }
    )
  }

  ngOnInit(): void {
    this.svc.checkifLoggedIn();
  }
  onAdd(){
    let newStaff=new staff(this.groupedForm.value.Name,this.groupedForm.value.StaffID, this.groupedForm.value.Dept)
    this.showname=this.groupedForm.value.Name;
    this.showdept=this.groupedForm.value.Dept;
    this.showstaffid=this.groupedForm.value.StaffID;
    this.svc.postStaff(newStaff);
    this.groupedForm.reset();
  }

}
