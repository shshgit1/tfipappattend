import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactsupportComponent } from './contactsupport.component';

describe('ContactsupportComponent', () => {
  let component: ContactsupportComponent;
  let fixture: ComponentFixture<ContactsupportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContactsupportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContactsupportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
