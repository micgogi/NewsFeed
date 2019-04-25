import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule, FormControl } from '@angular/forms';
import { LoginInfo } from '../login-info';
import { TokenstorageService } from '../tokenstorage.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JwtResponse } from '../jwt-response';
import { Router } from '@angular/router';
import { LoginServiceService } from '../login-service.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  submitted:boolean=false ;
  signInForm: FormGroup;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private loginInfo: LoginInfo;
  private loginUrl = 'http://localhost:45000/NewsFeedSearch/api/auth/signin';
  constructor(private fb:FormBuilder, private tokenStorage: TokenstorageService,
    private http: HttpClient,private router:Router,private loginservice:LoginServiceService) { }

  ngOnInit() {
    this.signInForm=this.fb.group({
  username:['',[Validators.required,Validators.minLength(2)]],
  password:['',[Validators.required,Validators.minLength(6)]]
  })

  if (this.tokenStorage.getToken()) {
    this.isLoggedIn = true;
    this.loginservice.isLoggedIn=true;
    this.roles = this.tokenStorage.getAuthorities();
  }
}

onSubmit(){
  this.submitted=true;
  console.log(this.signInForm.value);

if(this.signInForm.invalid)
    return; 

   
else{
  this.attemptAuth(this.signInForm.value).subscribe(
    data=>{
      this.tokenStorage.saveToken(data.accessToken);
      this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
     
        console.log()
        this.roles = this.tokenStorage.getAuthorities();
        alert("Success Fully Logged In!")
  console.log(this.roles[0])
  if(this.roles[0]="ROLE_USER"){
    this.reloadPage();
  }else{

       this.router.navigateByUrl('mic/admin');
  }
    
    },
    error=>{
      console.log(error);
     

      this.errorMessage = error.error.message;
      console.log(this.errorMessage);
      if(error.status=400)
        alert(this.errorMessage);
      if(error.status==0||error.status==504)
        alert("Unknown error"); 
      this.isLoginFailed = true;
      this.errorMessage="";
    }


  );

  }


}


reloadPage() {
 this.router.navigateByUrl('/news');
}
attemptAuth(credentials: LoginInfo): Observable<JwtResponse> {
  return this.http.post<JwtResponse>(this.loginUrl, credentials);
}
get f()
{
  return this.signInForm.controls;
}  

// signup1(){
//   console.log("signup clicked");
//   this.router.navigate(['signup']);
// }
  }
