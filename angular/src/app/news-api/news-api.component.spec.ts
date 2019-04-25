import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsApiComponent } from './news-api.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { NewsApiService } from '../news-api.service';
import { AuthGuard } from '../auth.guard';
import { DebugElement } from '@angular/core';
import { By } from '@angular/platform-browser';
import { of } from  'rxjs';

describe('NewsApiComponent', () => {
  let component: NewsApiComponent;
  let fixture: ComponentFixture<NewsApiComponent>;
  let debugElement:DebugElement;
  let newsSevice: NewsApiService;
  let spyService;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsApiComponent ],
      imports : [ ReactiveFormsModule, HttpClientModule, RouterTestingModule,FormsModule ],
      providers:[NewsApiService,HttpClient,AuthGuard]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsApiComponent);
    component = fixture.componentInstance;
    debugElement = fixture.debugElement;
    newsSevice=debugElement.injector.get(NewsApiService);
   spyService= spyOn(newsSevice,'initArticles').and.returnValue({ subscribe: () => {} });
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('call service',()=>{
      let form = fixture.debugElement.query(By.css('form'));
      let serachField= component.searchForm.controls['search'];
      serachField.setValue('mahi');
      form.triggerEventHandler('Search',null);
      expect(spyService).toHaveBeenCalled();
  })



  fit('should save search after fetch from api',()=>{
    let data = {
      keyword:'violin',
      username:'abcnfdbmndfbmnb'
    };
    // spyOn(component,'sendKeyword').and.callFake(()=>{
    //  component.sendKeyword(data);
    // });
    let spy = spyOn(component,'sendKeyword').and.returnValue(of(true));
    component.sendKeyword(data);
    expect(spy).toHaveBeenCalled();
  });
});
