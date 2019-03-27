import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {

  @Input('tickers') public tickers;
  @Input('showInfo') public showInfo: number;
  @Output() public childEvent = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  openInfo(index: number) {
    if(this.showInfo !== index) {
      this.showInfo = index;
      this.childEvent.emit(this.showInfo);
    }
  }

  closeInfoTab() {
    this.showInfo = null;
    this.childEvent.emit(this.showInfo);
  }
}
