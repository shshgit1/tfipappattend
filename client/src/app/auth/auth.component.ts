import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AttendService } from '../attend.service';
import { adminuser } from '../staff';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  groupedForm:FormGroup;
  UserIDinput=new FormControl();
  Password=new FormControl();
  responsefromspring:string="aa";


  constructor(private fb:FormBuilder, private svc:AttendService, private router:Router) {
    this.groupedForm=this.fb.group(
      {
        Username:this.UserIDinput,
        Password:this.Password
      }
    )
  }

  ngOnInit(): void {
  }

  onLogin(){
    let admin=new adminuser(this.groupedForm.value.Username, this.groupedForm.value.Password);
    this.svc.authenticator(admin).then(
      result=>{
        this.svc.saveUser(result['subject']);
        this.svc.saveToken(result['token']);
      })
      .then(redirect=>this.router.navigate(['admin']))
      .catch(err=>alert("Wong username and/or password!"))

}
}
