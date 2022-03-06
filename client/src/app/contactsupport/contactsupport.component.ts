import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AttendService } from '../attend.service';
import { contactsupport } from '../staff';

@Component({
  selector: 'app-contactsupport',
  templateUrl: './contactsupport.component.html',
  styleUrls: ['./contactsupport.component.css']
})
export class ContactsupportComponent implements OnInit {

  groupedForm:FormGroup;

  invalidemail:string="Please enter a valid email address";

  EmailInput=new FormControl('',[Validators.required,Validators.email]);
  SubjectInput=new FormControl('',[Validators.required]);
  EnquiryInput=new FormControl('',[Validators.required]);
  NameInput=new FormControl('',[Validators.required]);

  constructor(private fb:FormBuilder, private svc:AttendService, private router:Router) {
    this.groupedForm=this.fb.group(
      {
        Name:this.NameInput,
        Email:this.EmailInput,
        Subject:this.SubjectInput,
        Enquiry:this.EnquiryInput
      }
    )
  }

  contact(){
    let contactusObject=new contactsupport(
      this.groupedForm.value.Name,
      this.groupedForm.value.Email,
      this.groupedForm.value.Subject,
      this.groupedForm.value.Enquiry
    )
  this.svc.ContactSupport(contactusObject)
  .then(result=>console.log(result))

  alert("Thank you for contacting us, we will get back to you within 2 business days.")
  this.router.navigate(['']);

  }

  ngOnInit(): void {
  }

}
