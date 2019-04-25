import { browser, by, element } from 'protractor';



export class SignUpPage{



    navigateTo(){
        return browser.get('/signup');
    }
    getTitleText(){
        return element(by.css('h3')).getText();
    }
    setUsername(username:string){
        element(by.id('username')).clear();
        element(by.id('username')).sendKeys(username);
    }
    setEmail(email:string){
        element(by.id('email')).clear();
        element(by.id('email')).sendKeys(email);
    }

    setPassword(password:string){
        element(by.id('password')).clear();
        element(by.id('password')).sendKeys(password);
        
    }

    clickSignup(){
        element(by.id('signup')).click();    }
}