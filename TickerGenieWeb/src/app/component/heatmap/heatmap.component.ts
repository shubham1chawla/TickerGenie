import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-heatmap',
  templateUrl: './heatmap.component.html',
  styleUrls: ['./heatmap.component.scss']
})
export class HeatmapComponent implements OnInit {

  @Input('alphamap') public alphamap;
  @Output() public childEvent = new EventEmitter();

  public plotData = {
    x: [],
    y: [],
    z: [],
    type: 'heatmap',
    colorscale: 'Viridis'
  };

  public graph = {
      data: [
          this.plotData    
      ],
      layout: {
        title: 'Tickers Overview', autosize: true}
  };

  constructor() { }

  ngOnInit() {
    let zValues = [];
    for (let [key, value] of Object.entries(this.alphamap)) {
      this.plotData.x.push(key);
      zValues.push(value);
    }
    this.plotData.z.push(zValues);
  }

  clickEvent(event) {
    if(event.points !== undefined) {
      const alpha: string = event.points[0].x;
      this.childEvent.emit(alpha);
    }
  }
}
