import { Component, OnInit } from '@angular/core';
import { TokenstorageService } from '../tokenstorage.service';
import { KeywordSearch } from '../keyword-search';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { ResourceLoader } from '@angular/compiler';

@Component({
  selector: 'app-search-history',
  templateUrl: './search-history.component.html',
  styleUrls: ['./search-history.component.css']
})
export class SearchHistoryComponent implements OnInit {
   private deleteKeywordURL = "http://localhost:45000/NewsFeedSearch/api/user/deleteSearchKeyword/"
  keywordSearch:any;
  private getAllKeywordUrl:string="http://localhost:45000/NewsFeedSearch/api/user/getAllSearchKeywords/";
  constructor(private tokenStorage:TokenstorageService,
    private _http:HttpClient) { }
errorMessage:string="";
  ngOnInit() {
    this.getDataObservable(this.getAllKeywordUrl+this.tokenStorage.getUsername()).subscribe(
      data => {
        this.keywordSearch = data;
        console.log("I CANT SEE DATA HERE: ", this.keywordSearch);
      }
  );
    
  }

deleteKeyword(id:number){
  console.log(id);
  console.log(this.deleteKeywordURL+id)
this._http.delete(this.deleteKeywordURL+id).subscribe(
  result => console.log(result),
  error => this.errorMessage = error
);
console.log("Delete Clicked");
 this.reload();
}
  
    
  getDataObservable(url:string) {
    return this._http.get(url);
    
    
       

    
}


reload(){
  this.getDataObservable(this.getAllKeywordUrl+this.tokenStorage.getUsername()).subscribe(
    data => {
      this.keywordSearch = data;
      console.log("I CANT SEE DATA HERE: ", this.keywordSearch);
    }
);
}
  // getAllKeyword(){
  //       this.http.get(this.getAllKeywordUrl+this.tokenStorage.getUsername())
  //       .subscribe((data:KeywordSearch)=>this.keywordSearch={
  //         keyword:data['keyword'],
  //         username:data['username']
          
  //       })
        

  // console.log(this.keywordSearch.keyword);
  // }
}
