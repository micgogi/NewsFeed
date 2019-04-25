import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupComponent } from './signup.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { componentNeedsResolution } from '@angular/core/src/metadata/resource_loading';

describe('SignupComponent', () => {
  let component: SignupComponent;
  let fixture: ComponentFixture<SignupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SignupComponent ],
      imports : [ ReactiveFormsModule, HttpClientModule, RouterTestingModule ],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('is form invalid'),()=>{
    let username = component.registerForm.controls['username']
    username.setValue('bdbhd')
    let email = component.registerForm.controls['email']
    email.setValue('bdbhd')
    let passowrd = component.registerForm.controls['passowrd']
    passowrd.setValue('1Bbbbbbb')
    expect(component.registerForm.valid).toBeFalsy();
  }
  it('is form invalid'),()=>{

  let username = component.registerForm.controls['username']
  username.setValue('bdbhd')
  let email = component.registerForm.controls['email']
  email.setValue('bdbhd')
  let passowrd = component.registerForm.controls['password']
  passowrd.setValue('12')
  expect(component.registerForm.valid).toBeFalsy();
  }

  it('email field is empty'),()=>{
    let username = component.registerForm.controls['username']
    username.setValue('bdbhd')
    let email = component.registerForm.controls['email']
    email.setValue('')
    let password =component.registerForm.setValue['password']
    password.setValue("123456789")
    expect(component.registerForm.valid).toBeFalsy();
  }

  it('password field is empty'),()=>{
    let username = component.registerForm.controls['username']
    username.setValue('bdbhd')
    let email = component.registerForm.controls['email']
    email.setValue('abc@gmail.com')
    let password =component.registerForm.setValue['password']
    password.setValue("")
    expect(component.registerForm.valid).toBeFalsy();

  }
  it('username is empty'),()=>{
    let username = component.registerForm.controls['username']
    username.setValue('')
    let email = component.registerForm.controls['email']
    email.setValue('abc@gmail.com')
    let password =component.registerForm.setValue['password']
    password.setValue("123456789")
    expect(component.registerForm.valid).toBeFalsy();
  }
  
});
