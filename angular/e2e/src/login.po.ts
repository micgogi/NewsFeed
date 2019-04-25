import { browser, element, by } from 'protractor';
export class LoginPage{
        navigateTo(){
            browser.get('login');
        }

        getTitleText(){
            return element(by.css('h3')).getText();
        }

        setUsername(username:string){
            return element(by.id('username')).sendKeys(username);
        }
        setPasswprd(password:string){
            return element(by.id('password')).sendKeys(password);
        }
        clickLogin(){
            element(by.id('login')).click();
        }
        clickSearchHistory(){
            element(by.id('searchHistory')).click();
        }

      
    
        clickSearchNews()
        {
            element(by.id('searchNutton')).click();
        }
    
        clickLogout()
        {
            element(by.id('logout')).click();
        }
    
        getTable()
        {
            return element(by.css('table'));
        }
            
        getTableRows()
        {
            return this.getTable().all(by.css('tr')).first();
        }
    
        getTableCell()
        {
            return this.getTableRows().all(by.css('td'));
        }
    
        clickTableButton()
        {
            this.getTableCell().all(by.css('button')).click();
        }
    

}