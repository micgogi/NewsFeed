import { Component, OnInit } from '@angular/core';

import { FormGroup, FormBuilder,FormControl} from '@angular/forms';
import { Router } from '@angular/router';
import { NewsApiService } from '../news-api.service';
import { HttpClient } from '@angular/common/http';
import { KeywordSearch } from '../keyword-search';
import { TokenstorageService } from '../tokenstorage.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-news-api',
  templateUrl: './news-api.component.html',
  styleUrls: ['./news-api.component.css']
})
export class NewsApiComponent implements OnInit {
  searchH=false;
  MainContent=true;
  searchForm: FormGroup;
  mArticles:Array<any>;
  mSources:Array<any>;
  page:number=1;
  username:string;
  errorMessage:string = '';
  searchKeyword:KeywordSearch;
  private keywordSaveURL="http://localhost:45000/NewsFeedSearch/api/user/saveKeyword"

  constructor(private formBuilder: FormBuilder, 
    private router: Router, private newsapi:NewsApiService,private http:HttpClient,private tokenStorage:TokenstorageService) {  
  }

  ngOnInit() {
            //load articles
            this.newsapi.initArticles().subscribe(data => this.mArticles = data['articles']);
            //load news sources
            this.newsapi.initSources().subscribe(data=> this.mSources = data['sources']);
            this.searchForm = this.formBuilder.group({
                  search:new FormControl()
                 });
            
  }

  searchArticles(source:any){
    console.log("selected source is: "+source.search);
    this.username= this.tokenStorage.getUsername();
    
    
    this.searchKeyword = new KeywordSearch(source.search,this.tokenStorage.getUsername());
    this.sendKeyword(this.searchKeyword).subscribe(
      data=>{
        console.log(data);
        
       
      },
      error => {
       console.log(error);
       this.errorMessage = error.error.message;
  
     }
    );
 
  console.log("clocked searchArticles");
    this.newsapi.getArticlesByID(source.search).subscribe(data => this.mArticles = data['articles']);
  }

  SearchHistory() {
    console.log("Search History clicked");
    this.searchH=true;
    this.MainContent=false;
    this.router.navigateByUrl('searchHistory');
    
  }

  loadMore(){
    console.log("load more clicked");

this.newsapi.initArticles().subscribe(data => this.mArticles = data['articles']);

this.newsapi.initSources().subscribe(data=> this.mSources = data['sources']);
  }

  sendKeyword(info:KeywordSearch): Observable<string> {
   
      return this.http.post<string>(this.keywordSaveURL,info);
    }
  }


