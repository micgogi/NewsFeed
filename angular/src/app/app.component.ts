import { Component } from '@angular/core';
import { TokenstorageService } from './tokenstorage.service';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private roles: string[];
   authority: string;
   isLogged:boolean=false;
   isNotLogged:boolean=false;

  constructor(private tokenStorage: TokenstorageService,private router: Router) { 
    if(tokenStorage.getToken()){
      this.isLogged=true;
      this.isNotLogged=false;
    }
    if(tokenStorage.getToken()==null){
      this.isNotLogged=true;
      this.isLogged=false;
    }
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        } else if (role === 'ROLE_PM') {
          this.authority = 'pm';
          return false;
        }
        this.authority = 'user';
        return true;
      });
    }
  }
  logout(){
    console.log("signed out")
    this.tokenStorage.signOut();
    this.router.navigateByUrl('/login');
  }
  check(){
   
    if(localStorage.getItem('loggedIn')){
      return true;
    }else{
      return false;
    }
  }
}
