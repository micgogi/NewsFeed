import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Signupinfo } from '../signupinfo';
import { ResourceLoader } from '@angular/compiler';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
private userUrl="http://localhost:45000/NewsFeedSearch/api/user/getAllUsers"
private deleteUrl="http://localhost:45000/NewsFeedSearch/api/user/blockUserById/"
private searchUrl="http://localhost:45000/NewsFeedSearch/api/user/searchUser/"
private errorMessage="";
private searchValue1="";
  constructor(private _http:HttpClient,private formBuilder: FormBuilder) { }
signUpInfo:any;
searchForm: FormGroup;
  ngOnInit() {
    this.searchForm = this.formBuilder.group({
          search:new FormControl()
         });
    this.getDataObservable(this.userUrl).subscribe(
      data => {
        this.signUpInfo= data;
        console.log("I CAN SEE DATA HERE: ", this.signUpInfo);
      }
  );
  }


  getDataObservable(url:string) {
    return this._http.get(url);
  }


  search(searchValue:string){
    console.log(searchValue);
   this.searchValue1=""+searchValue.search
    this.getDataObservable(this.searchUrl+searchValue.search).subscribe(
      data => {
        this.signUpInfo= data;
        console.log("Serach Data ", this.signUpInfo);
      }
  );
  this.reload();
  }
reload(){
  this.getDataObservable(this.userUrl).subscribe(
    data => {
      this.signUpInfo= data;
      console.log("I CAN SEE DATA HERE: ", this.signUpInfo);
    }
);
}
block(id:number){
  
    this._http.get(this.deleteUrl+id).subscribe(
    data => {
      this.signUpInfo= data;})
  console.log("After deleting", this.signUpInfo);
console.log("Block Clicked");
this.reload();
    }
}
