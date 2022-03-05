import { Component, OnInit } from '@angular/core';
import { AttendService } from '../attend.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private svc :AttendService) { }

  ngOnInit(): void {
    this.svc.checkifLoggedIn();

  }
  logout(){
    this.svc.signOut()
  }

}
