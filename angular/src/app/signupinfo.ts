export class Signupinfo {

    
    username: string;
    email: string;
    role: number;
    password: string;


    constructor( username: string, email: string, password: string) {
        
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = 2;
    }
}
