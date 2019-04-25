import { Injectable } from '@angular/core';
import { HttpClient  } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class NewsApiService {
  private keywordSaveURL="http://localhost:46001/NewsFeedSearch/api/user/saveKeyword"
  api_key = '38710738aaa9430a897b6f45d8d89fe0';
   pageSize:number=1;
  constructor(private http:HttpClient) { }
  initSources(){
     return this.http.get('https://newsapi.org/v2/sources?language=en&apiKey='+this.api_key);
  }
  initArticles(){

   return this.http.get('https://newsapi.org/v2/top-headlines?pageSize=8&page='+this.pageSize+++'&sources=bbc-news,the-verge,abc-news&apiKey='+this.api_key);
  }
  getArticlesByID(source: String){
   return this.http.get('https://newsapi.org/v2/everything?' +
   'q='+source+
   '&from=2019-02-01&' +
   'sortBy=popularity&apiKey='+this.api_key);
  }
  sendKeyword(){

  }
} 
