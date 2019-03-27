import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router'

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private LOGIN_API_URL: string = "http://localhost:8080/api/auth/login";
  public userInfo = null;

  constructor(private _http: HttpClient,
              private _router: Router) { }

  setSession() {
    sessionStorage.setItem("USER_INFO", JSON.stringify(this.userInfo));
  }

  destroySession() {
    sessionStorage.removeItem("USER_INFO");
  }

  getUserSessionInfo() {
    return sessionStorage.getItem("USER_INFO");
  }

  isSessionSet(): boolean {
    return (this.getUserSessionInfo()) ? true : false;
  }

  authUser(username: string, password: string) {
    return this._http.post(this.LOGIN_API_URL, {"username":username,"password":password}, httpOptions);
  }

  deAuthUser() {
    this.destroySession();
    this._router.navigate(['/login']);
  }

}
