import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule, FormControl } from '@angular/forms';
import { AuthServiceService } from '../auth-service.service';
import { Signupinfo } from '../signupinfo';
import { Observable } from 'rxjs';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';



// const httpOptions = {
//   headers: new HttpHeaders({ 'Content-Type': 'application/json' })
// };
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})




export class SignupComponent implements OnInit {

  
  

  submitted:boolean = false;
  registerForm: FormGroup;
  signupInfo: Signupinfo;
  isSignedUp:boolean = false;
  isSignUpFailed:boolean = false;
  errorMessage:string = '';
  role = new Array<String>();

  private signupUrl = 'http://localhost:45000/NewsFeedSearch/api/auth/signup';
  constructor(private authService: AuthServiceService,private formBuilder: FormBuilder,
    private http: HttpClient,private router:Router) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
    
      username: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    })
  }
  get f()
  {
    return this.registerForm.controls;
  }  

  onSubmit(){
    this.submitted=true;
    if(this.registerForm.invalid){
      return;
    }
    this.signupInfo=new Signupinfo(
    this.registerForm.get('username').value,this.registerForm.get('email').value,
    this.registerForm.get('password').value);
    console.log(this.registerForm.value);
   this.signUp(this.signupInfo).subscribe(
     data=>{
       console.log(data);
       alert('Successfully Registered')
       this.isSignedUp = true;
       this.isSignUpFailed = false;
       this.router.navigateByUrl('/login');
     },
     error => {
      console.log(error);
      if(error.status == 400)
        alert("Something went wrong! Please try again later.");
      if(error.status==0||error.status==504)
        alert("Unknown error");  
      this.errorMessage = error.error.message;
      this.isSignUpFailed = true;
    }
   );
   console.log(this.isSignedUp);
   if(this.isSignedUp){
     
      console.log("INSIGNEDUP")
    }
  }

  signUp(info:Signupinfo): Observable<string> {
    console.log(info+""+info.password);
    return this.http.post<string>(this.signupUrl,info);
  }
}
