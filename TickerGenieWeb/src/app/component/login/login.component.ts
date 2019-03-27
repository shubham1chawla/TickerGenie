import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth.service'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private _auth: AuthService, private _router: Router) { }

  ngOnInit() {
  }

  loginFormHandler(event) {
    event.preventDefault();

    let username = event.target.username.value;
    let password = event.target.password.value;

    this._auth.authUser(username, password).subscribe(
      res => {
        this._auth.userInfo = res;
        if(this._auth.userInfo.id !== null) {
          this._auth.setSession();
          this._router.navigate(['/dashboard']);
        }
        else{
          this._auth.userInfo = null;
          window.alert("Incorrect Credentials");
        }
      },
      error => {
        window.alert("App Server's Down, Please contact Support");
      }
    );
  }

}
