import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { GoogleChartsModule } from 'angular-google-charts';
import { PlotlyModule } from 'angular-plotly.js';

import { AppComponent } from './app.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { TickerInfoComponent } from './component/ticker-info/ticker-info.component';
import { LoginComponent } from './component/login/login.component';

import { DashboardService } from './service/dashboard.service';
import { HeatmapComponent } from './component/heatmap/heatmap.component';
import { TableComponent } from './component/table/table.component';
import { TreemapComponent } from './component/treemap/treemap.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    TickerInfoComponent,
    LoginComponent,
    HeatmapComponent,
    TableComponent,
    TreemapComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    GoogleChartsModule,
    PlotlyModule
  ],
  providers: [DashboardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
