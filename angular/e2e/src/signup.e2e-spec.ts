import { browser, protractor } from 'protractor';
import { SignUpPage } from './signup.po';





fdescribe('signup  component',()=>{

    let signUp:SignUpPage

    beforeEach(()=>{
     signUp=new SignUpPage();
     signUp.navigateTo();
    })

    it('should display heading',()=>{

        expect(signUp.getTitleText()).toEqual('Registration Form');

    })


    // it('sign up with new user data',()=>{

    //     signUp.setUsername('ygvtfcu');
    //     signUp.setPassword('123456789');
    //     signUp.setEmail("ygvtfcu@gmail.com");
    //     signUp.clickSignup();
    //     browser.wait(protractor.ExpectedConditions.alertIsPresent(),20000)
    //     expect(browser.switchTo().alert().getText()).toEqual("Successfully Registered");
    //     browser.switchTo().alert().accept();
    //     // expect(browser.getCurrentUrl()).toEqual('http:localhost:4200/login');
    // })

    it('check register with already existing data',()=>
    {
       signUp.setUsername('rahul');
       signUp.setEmail('rahul@gmail.com');
       signUp.setPassword('123456789');
       signUp.clickSignup();
        browser.wait(protractor.ExpectedConditions.alertIsPresent(),20000);
        expect(browser.switchTo().alert().getText()).toEqual("Something went wrong! Please try again later.");
        browser.switchTo().alert().accept();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/signup');
    })


    it('user sign Up when server down',()=>{

        signUp.setUsername('klbgfhfhbhhm');
        signUp.setPassword('kgfmgfmhnmh');
        signUp.setEmail('abc@gmail.com')
        signUp.clickSignup()
        browser.wait(protractor.ExpectedConditions.alertIsPresent(),1000);
        expect(browser.switchTo().alert().getText()).toEqual("Unknown error");
    browser.switchTo().alert().accept();
   expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/signup');
      })


})
