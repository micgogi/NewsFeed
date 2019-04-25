import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {
  isLoggedIn:boolean;
  constructor() { }
}
