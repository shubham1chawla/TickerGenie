import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../service/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private _authService: AuthService,
              private _router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    let urlSeg = next.url[0];
    if(urlSeg.path === 'dashboard') {
      if(this._authService.isSessionSet()) {
        return true;
      }
      else {
        this._router.navigate(['/login']);
        return false;
      }
    }
    else if(urlSeg.path === 'login'){
      if(!this._authService.isSessionSet()) {
        return true;
      }
      else {
        this._router.navigate(['/dashboard']);
        return false;
      }
    }
  }
}
