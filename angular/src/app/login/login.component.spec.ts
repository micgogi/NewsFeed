import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { ReactiveFormsModule, FormGroup, FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { LoginServiceService } from '../login-service.service';
import { TokenstorageService } from '../tokenstorage.service';
import { By } from '@angular/platform-browser';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent],
      imports : [ ReactiveFormsModule, HttpClientModule, RouterTestingModule ,FormsModule],
      providers:[LoginServiceService,TokenstorageService,HttpClient]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should create form',()=>{
    expect(component.signInForm.contains('username')).toBeTruthy();
    expect(component.signInForm.contains('password')).toBeTruthy();
      });

      it('should create form controls',()=>{
        let name=component.signInForm.get('username');
        name.setValue('');
        expect(name.valid).toBeFalsy();
        name.setValue('user123');
        expect(name.valid).toBeTruthy();
        let password=component.signInForm.get('password');
        password.setValue('');
        expect(password.valid).toBeFalsy();
        password.setValue('user123456');
        expect(password.valid).toBeTruthy();
      });

      it('form submit test',()=>{
        let service=TestBed.get(LoginServiceService);
        // let spy=spyOn(service,'attemptAuth').and.returnValue({subscribe:()=>{return 'Success'}});
        let name=component.signInForm.get('username');
        name.setValue('user123');
        let password=component.signInForm.get('password');
        password.setValue('user123456');
        let form=fixture.debugElement.query(By.css('form'));
        form.triggerEventHandler('submit',null);
        fixture.detectChanges();
        // expect(spy).toHaveBeenCalled();
      })


});
