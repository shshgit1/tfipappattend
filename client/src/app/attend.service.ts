import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, lastValueFrom, Observable, throwError } from 'rxjs';
import {adminuser, clockinout, contactsupport, staff, timesheet} from "./staff";
@Injectable({
  providedIn: 'root'
})
export class AttendService {

  constructor(private http:HttpClient,private router:Router) { }

  postStaff(staffa:staff): Promise<any>{
    return lastValueFrom(this.http.post<any>("http://localhost:8080/attendance/add",staffa))

  }
  getallStaff():Promise<staff[]>{
    return lastValueFrom(
      this.http.get<staff[]>("http://localhost:8080/attendance/listall")
    )
  }

  clockin(staffid:string,lat:any,lng:any){
    let clockinobject=new clockinout(staffid,lat,lng);
    return lastValueFrom(this.http.post<any>("http://localhost:8080/attendance/clockin",clockinobject))
  }

  clockout(staffid:string,lat:any,lng:any){
    let clockoutobject=new clockinout(staffid,lat,lng);
    return lastValueFrom(this.http.post<string>("http://localhost:8080/attendance/clockout",clockoutobject))
  }

  getStaffTimeSheet(staffid:string):Promise<timesheet[]>{
    let url='http://localhost:8080/attendance/timesheet/'+staffid;
    return lastValueFrom(
      this.http.get<timesheet[]>(url)
    )
  }

  filterStaffTimeSheet(staffid:string, start:string, end:string):Promise<timesheet[]>{
    let url='http://localhost:8080/attendance/timesheetfilter/'+staffid+'?start='+start+'&end='+end;
    return lastValueFrom(
      this.http.get<timesheet[]>(url)
    )
  }

  removeStaff(staffid:string){
    let url='http://localhost:8080/attendance/remove/';
    return lastValueFrom(
      this.http.post<string>((url),staffid)
    )
  }

  authenticator(admin:adminuser){
    let url='http://localhost:8080/attendance/auth/';
    return lastValueFrom(
      this.http.post<any>((url),admin)
    )
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem('token');
    window.sessionStorage.setItem('token', token);
  }

  public getToken(): string | null {
    return window.sessionStorage.getItem('token');
  }

  public saveUser(user: any): void {
    window.sessionStorage.removeItem('subject');
    window.sessionStorage.setItem('subject', user);
  }

  public getUser(): any {
    const user = window.sessionStorage.getItem('token');
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }
  public checkifLoggedIn(){
    let adminlogin=window.sessionStorage.getItem('subject');
    if (adminlogin!='admin'){
      alert("Please log in");
      this.router.navigate(['auth']);
    }
  }

  signOut(): void {
    window.sessionStorage.clear();
    this.router.navigate(['']);
  }

  ContactSupport(contactsupport:any){
    return lastValueFrom(this.http.post('http://localhost:8080/attendance/contactus', contactsupport))
  }

  getTenYearYield():any{
    return lastValueFrom(
      this.http.get<any>("http://localhost:8080/attendance/getInterest")
    )
  }


}
