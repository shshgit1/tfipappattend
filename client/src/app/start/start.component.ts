import { Component, OnInit } from '@angular/core';
import { AttendService } from '../attend.service';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MapsAPILoader } from '@agm/core';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {
  groupedForm:FormGroup;
  StaffIdInp=new FormControl();
  RemarksInp=new FormControl();
  currentInterestRate:string="";

  lat!:any;
  lng!:any;

  constructor(private fb:FormBuilder, private attsvc:AttendService, private mapsAPILoader: MapsAPILoader,) {
    this.groupedForm=this.fb.group(
      {
      StaffIdinput:this.StaffIdInp,
      Remarks:this.RemarksInp
      }
    )
  }

  ngOnInit(): void {
    if (navigator)
    {
    navigator.geolocation.getCurrentPosition( pos => {
        this.lng = +pos.coords.longitude;
        this.lat = +pos.coords.latitude;
      });
    }

        this.attsvc.getTenYearYield().then((result: string)=>this.currentInterestRate=result)
  }

  clockin(){
    this.attsvc.clockin(this.groupedForm.value.StaffIdinput,this.lat,this.lng)
    .then((response)=>
    {
      console.log(response);
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
    .catch(err=>alert("Staff ID does not exist."))

    this.groupedForm.reset();
  }
  clockout(){
    this.attsvc.clockout(this.groupedForm.value.StaffIdinput,this.lat,this.lng) .then((response)=>
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
    .catch(err=>alert("Staff ID does not exist."))
    this.groupedForm.reset();
  }

  get() {

}

}
