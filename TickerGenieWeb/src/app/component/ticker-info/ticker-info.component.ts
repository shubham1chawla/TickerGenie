import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { DashboardService } from 'src/app/service/dashboard.service';

@Component({
  selector: 'app-ticker-info',
  templateUrl: './ticker-info.component.html',
  styleUrls: ['./ticker-info.component.scss']
})
export class TickerInfoComponent implements OnInit {

  @Input('tickerInfo') public tickerInfo;
  @Output() public closeEvent = new EventEmitter();
  public tickerQuandlInfo = null;
  public showInfo: boolean = false;
  private _errorMessage: string = null;

  constructor(private _dashService: DashboardService) { }

  ngOnInit() {
    this._dashService.getInfo(this.tickerInfo.code).subscribe(
      res => {
        this._errorMessage = null;
        this.tickerQuandlInfo = res;
        this.showInfo = true;
      },
      error => {
        this._errorMessage = "Internal Server error occured. Please try again later.";
        this.tickerQuandlInfo = null;
        this.showInfo = true;
      }
    );
  }

  isError() {
    if(!this.tickerQuandlInfo && this._errorMessage) {
      return true;
    }
    else if(this.tickerQuandlInfo['error']) {
      return true;
    }
    else {
      return false;
    }
  }

  printErrorMessage() {
    if(this.tickerQuandlInfo) {
      return this.tickerQuandlInfo['error'].message;
    }
    else {
      return this._errorMessage;
    }
  }

  closeInfo() {
    this.closeEvent.emit(true);
  }

}
