export class staff{

  constructor(
  public name:string,
  public staffid:string,
  public department:string,
){}

}

export interface timesheet{

  name:string;
  staffid:string;
  department:string;
  Date:string;
  clock_in:string;
  clock_out:string
}

export class adminuser{
constructor(
  public username:string,
  public password:string
){}
  }

  export class contactsupport{
    constructor(
      public name:string,
      public email: string,
      public subject: string,
      public enquiry: string
    )   {}
  }
