import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../service/dashboard.service';
import { AuthService } from 'src/app/service/auth.service';

const Views = {
  TABLE: "table",
  HEATMAP: "heatmap",
  LOADING: "loading"
}

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  public tickers = null;
  public showInfo = null;
  public hideSidebar: boolean = false;
  public totalCount = null;
  public userInfo = null;
  public currentView: string = Views.LOADING;
  public alphamap = null;
  public appServerDownMsg: string = null;

  constructor(private _dashService: DashboardService,
              private _authService: AuthService) { }

  ngOnInit() {
    this._dashService.getTotalCount().subscribe(
      res => {
        this.appServerDownMsg = null;
        this.totalCount = res;
        this._dashService.getAlphaMap().subscribe(
          res => {
            this.alphamap = res;
            this.currentView = Views.HEATMAP;
          }
        );
      },
      error => {
        console.log(error.message);
        this.appServerDownMsg = error.message;
      }
    );
    this.userInfo = JSON.parse(this._authService.getUserSessionInfo());
  }

  getTickers(start: number, count: number) {
    this._dashService.getTickers(start, count).subscribe(
      res => {
        this.tickers = res
      }
    );
  }

  toggleSidebar() {
    this.hideSidebar = !this.hideSidebar;
  }

  searchFormHandler(event) {
    event.preventDefault();

    this.showInfo = null;
    this.hideSidebar = (window.innerHeight > window.innerWidth) ? true : false;
    if(!this.isTable()) this.toggleView();

    let query = event.target.search_query.value;
    if(query !== null || query.length !== 0) {
      this._dashService.getTickersByQuery(query).subscribe(
        res => {
          this.tickers = res;
        }
      );
    }
    else{
      this.getTickers(null, null);
    }
  }

  refineFormHandler(event) {
    event.preventDefault();

    this.showInfo = null;
    this.hideSidebar = (window.innerHeight > window.innerWidth) ? true : false;
    if(!this.isTable()) this.toggleView();

    let start = event.target.start.value;
    let count = event.target.count.value;

    if(start < 0 || start >= this.totalCount) {
      event.target.start.value = start = 0;
    }
    if(count < 0 || count >= this.totalCount) {
      event.target.count.value = count = 15;
    }

    this.getTickers((start.length !== 0) ? start : null, 
                    (count.length !== 0) ? count : null);
  }

  triggerLogout() {
    this._authService.deAuthUser();
  }

  isTable(): boolean {
    if(this.currentView === Views.TABLE) {
      return true;
    }
    else {
      return false;
    }
  }

  isLoading(): boolean {
    if(this.currentView === Views.LOADING) {
      return true;
    }
    else {
      return false;
    }
  }

  toggleView() {
    if(this.alphamap) {
      if(this.isTable()) {
        this.currentView = Views.HEATMAP;
      }
      else {
        this.currentView = Views.TABLE;
      }
    }
    else {
      this.currentView = Views.LOADING;
    }
  }

  heatmapClickHandler(alphabet: string) {
    this._dashService.getAlphaList(alphabet).subscribe(
      res => {
        this.showInfo = null;
        if(!this.isTable()) this.toggleView();
        this.tickers = res;
      }
    );
  }

  isAppServerDown(): boolean {
    return (this.appServerDownMsg !== null) ? true : false;
  }

}
