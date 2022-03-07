export class staff{

  constructor(
  public name:string,
  public staffid:string,
  public department:string,
){}
}

export class clockinout{

  constructor(
  public staffid:string,
  public lat:any,
  public lng:any
){}
}


export interface timesheet{

  name:string;
  staffid:string;
  department:string;
  Date:string;
  clock_in:string;
  clock_out:string;
  clock_in_location:string;
  clock_out_location:string
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
