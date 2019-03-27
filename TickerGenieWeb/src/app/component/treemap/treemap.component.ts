import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-treemap',
  templateUrl: './treemap.component.html',
  styleUrls: ['./treemap.component.scss']
})
export class TreemapComponent implements OnInit{

  @Input('alphamap') public alphamap;
  @Output() public childEvent = new EventEmitter();

  title = 'Alphabetical Ticker Volume';
  type='TreeMap';
  data = [];
  columnNames = ["Location", "Parent","Market trade volume (size)","Market increase/decrease (color)"];
  options = { 
    minColor:"#e74c3c",
    midColor:'#f39c12',
    maxColor:'#27ae60',
    headerHeight:15,
    showScale:false,
    generateTooltip: this.showFullTooltip
  };
  width = this.calcWidth();
  height = this.calcHeight();

  constructor() { }

  ngOnInit() {
    this.data.push(['Alphabets', null, 0, 0]);

    let maxCount = this.alphamap['A'];
    for(let [key, value] of Object.entries(this.alphamap)) {
      maxCount = (maxCount >= value) ? maxCount : value;
    }

    for (let key in this.alphamap) {
      this.data.push([key, 'Alphabets', this.alphamap[key], (this.alphamap[key]-maxCount)]);
    } 
  }

  showFullTooltip(row, size, value) {
    let alpha = String.fromCharCode(97 + row - 1).toUpperCase();
    return '<div style="font-family: \'Fjalla One\', sans-serif;font-size:0.8rem;padding: 20px;background-color: rgba(255, 255, 255, 0.9);color: black;box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.2);border-radius:10px;">'+
    'Ticker\'s name starting with: <strong>' + alpha + '</strong><br>' +
    'Total Volumne: <strong>' + size + '</strong>' +
    '<div>';
  }

  onTooltipBtnClick(event) {
    const alpha: string = String.fromCharCode(97 + event[0].row - 1).toUpperCase();
    this.childEvent.emit(alpha);
  }

  calcWidth() {
    if(window.innerWidth > window.innerHeight) {
      return Math.ceil(window.innerWidth*0.685);
    }
    else {
      return Math.ceil(window.innerWidth*0.975);
    }
  }

  calcHeight() {
    return Math.ceil(window.innerHeight*0.85);
  }

}
