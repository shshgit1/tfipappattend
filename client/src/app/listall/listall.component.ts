import { Component, OnInit } from '@angular/core';
import { AttendService } from '../attend.service';
import { staff } from '../staff';

@Component({
  selector: 'app-listall',
  templateUrl: './listall.component.html',
  styleUrls: ['./listall.component.css']
})
export class ListallComponent implements OnInit {

  constructor(private attsvc:AttendService) {

  }
  staff:staff[]=[];

  ngOnInit() {
    this.attsvc.checkifLoggedIn();
    this.attsvc.getallStaff().then(result=>this.staff=result);
  }
  remove(staffid:string){

    this.attsvc.removeStaff(staffid);
    alert("StaffID "+staffid+ " has been removed.")
    window.location.reload();

  }
}
