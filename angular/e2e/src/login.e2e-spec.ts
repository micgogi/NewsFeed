import {LoginPage} from './login.po'
import {browser, protractor}  from 'protractor'


describe('login component test',()=>{
  let loginPage:LoginPage

        beforeEach(()=>{

            loginPage= new LoginPage();
            loginPage.navigateTo();
        })

        it('check login heading',()=>{

            expect(loginPage.getTitleText()).toEqual('Login');

        })


   it('check user with login data ',()=>{
            loginPage.setUsername('lkjhgfdsa');
            loginPage.setPasswprd('123456789');
            loginPage.clickLogin();
            browser.wait(protractor.ExpectedConditions.alertIsPresent(),10000)
            expect(browser.switchTo().alert().getText()).toEqual("Success Fully Logged In!")
            browser.switchTo().alert().accept();
             expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/news');

             loginPage.clickSearchHistory();
            expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/searchHistory');
             expect(loginPage.getTable().isPresent()).toBeTruthy();
            
            // loginPage.clickTableButton();

            // browser.wait(protractor.ExpectedConditions.alertIsPresent(),1000)
            // expect(browser.switchTo().alert().getText()).toEqual("Search deleted");
            // browser.switchTo().alert().accept();
            // expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/searchHistory');
            // loginPage.clickSearchNews();
            // expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/news');






   })


   it('check for blackListed user',()=>{


    loginPage.setUsername('zxcvbnm');
    loginPage.setPasswprd('123456789');
    loginPage.clickLogin();
    browser.wait(protractor.ExpectedConditions.alertIsPresent(),10000);
    expect(browser.switchTo().alert().getText()).toEqual("You are Blocked by admin");
    browser.switchTo().alert().accept();
    // expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/login');

   })

   it('user sign in with invalid data',()=>{

    loginPage.setUsername('klbgfhfhbhhm');
    loginPage.setPasswprd('kgfmgfmhnmh');
    loginPage.clickLogin();
    browser.wait(protractor.ExpectedConditions.alertIsPresent(),1000);
    expect(browser.switchTo().alert().getText()).toEqual("No User Found");
    // browser.switchTo().alert().accept();
    // expect(browser.getCurrentUrl()).toEqual("http://localhost:4200/login");
   })

    it('successfully logged out',()=>{
     loginPage.setUsername('lkjhgfdsa');
     loginPage.setPasswprd('123456789');
     loginPage.clickLogin();
     browser.wait(protractor.ExpectedConditions.alertIsPresent(),10000);
     browser.switchTo().alert().accept();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/news');

        loginPage.clickLogout();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/login');



    })


    it('user sign in when server down',()=>{

           loginPage.setUsername('klbgfhfhbhhm');
           loginPage.setPasswprd('kgfmgfmhnmh');
           loginPage.clickLogin();
           browser.wait(protractor.ExpectedConditions.alertIsPresent(),1000);
           expect(browser.switchTo().alert().getText()).toEqual("Unknown error");
       browser.switchTo().alert().accept();
      expect(browser.getCurrentUrl()).toEqual("http://localhost:4200/login");
         })

})