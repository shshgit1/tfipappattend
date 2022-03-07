import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AttendService } from '../attend.service';
import { timesheet } from '../staff';

@Component({
  selector: 'app-timesheet',
  templateUrl: './timesheet.component.html',
  styleUrls: ['./timesheet.component.css']
})
export class TimesheetComponent implements OnInit {

  groupedForm:FormGroup;

  StartD=new Date;
  EndD=new Date;

  StartDate=new FormControl(this.StartD);
  EndDate=new FormControl(this.EndD);

  IdOfStaff:any;
  timeSheetOutput:timesheet[]=[];

  constructor(private aroute:ActivatedRoute, private attsvc:AttendService,
    private fb:FormBuilder, private pipedate:DatePipe) {
    this.groupedForm=this.fb.group(
    { startD:this.StartDate,
      endD:this.EndDate
    }
    )
  }

  ngOnInit(): void {
    this.attsvc.checkifLoggedIn();
    this.IdOfStaff = this.aroute.snapshot.params['staffid'];
    this.attsvc.getStaffTimeSheet(this.IdOfStaff).then(result=>
      {console.log("aaa>" + this.timeSheetOutput)
        this.timeSheetOutput=result
      }
      );

  }
  runFilter():void{
    let start:string="";
    start=this.pipedate.transform(this.groupedForm.value.startD, 'yyyy-MM-dd') as string;
    let end:string="";
    end=this.pipedate.transform(this.groupedForm.value.endD, 'yyyy-MM-dd') as string;

    this.attsvc.filterStaffTimeSheet
    (this.IdOfStaff,start,end)
    .then(result=>this.timeSheetOutput=result);
  }

}
