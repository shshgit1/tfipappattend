import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { StartComponent } from './start/start.component'
import { AddComponent } from './add/add.component';
import { AttendService } from './attend.service';
import { ListallComponent } from './listall/listall.component';
import { AdminComponent } from './admin/admin.component';
import { TimesheetComponent } from './timesheet/timesheet.component';

import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { AgmCoreModule } from '@agm/core';


import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { MAT_MOMENT_DATE_ADAPTER_OPTIONS, MAT_MOMENT_DATE_FORMATS,
  MomentDateAdapter } from '@angular/material-moment-adapter';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DatePipe } from '@angular/common';
import { AuthComponent } from './auth/auth.component';
import { ContactsupportComponent } from './contactsupport/contactsupport.component';
import { ServiceWorkerModule } from '@angular/service-worker';
import { environment } from '../environments/environment';


const appRoutes: Routes = [
  {path: '', component : StartComponent},
  {path: 'admin/add', component: AddComponent},
  {path: 'admin/listall', component:ListallComponent},
  {path : 'admin', component: AdminComponent},
  {path :'admin/timesheet/:staffid', component: TimesheetComponent},
  {path: 'auth', component: AuthComponent},
  {path: 'contactsupport', component:ContactsupportComponent}

]

@NgModule({
  declarations: [
    AppComponent,
    StartComponent,
    AddComponent,
    ListallComponent,
    AdminComponent,
    TimesheetComponent,
    AuthComponent,
    ContactsupportComponent,

  ],
  imports: [
    BrowserModule,
    AgmCoreModule.forRoot({apiKey: 'AIzaSyDVS-RVgZeAx1p_o4bQ7w19WBlRiuFQWmU'}),
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
    MatDatepickerModule,
    MatFormFieldModule,
    MatInputModule,
    BrowserAnimationsModule,
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: environment.production,
      // Register the ServiceWorker as soon as the app is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000'
    })
  ],

  providers: [AttendService,
    { provide: MAT_DATE_LOCALE, useValue: 'en-SG'},
    { provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS]
    },
    { provide: MAT_DATE_FORMATS,
      useValue: MAT_MOMENT_DATE_FORMATS
    },
    { provide: MAT_MOMENT_DATE_ADAPTER_OPTIONS,
      useValue: { useUtc: true }
    },
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
