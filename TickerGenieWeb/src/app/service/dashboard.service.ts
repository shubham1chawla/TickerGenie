import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private TICKER_API_URL: string = "http://localhost:8080/api/tickers"; 
  private SEARCH_API_URL: string = "http://localhost:8080/api/tickers/search?query=";
  private COUNT_API_URL: string = "http://localhost:8080/api/tickers/count";
  private ALPHA_MAP_API: string = "http://localhost:8080/api/tickers/map/alpha";
  private ALPHA_LIST_API: string = "http://localhost:8080/api/tickers/alpha/";

  constructor(private _http: HttpClient) { }

  getTickers(start: number, count: number) {
    let url = this.TICKER_API_URL;
    url += (start && count) ? "?start="+start+"&count="+count : (start && !count) ? "?start="+start : (count && !start) ? "?count="+count : "";
    return this._http.get(url);
  }

  getInfo(code: string) {
    let url = this.TICKER_API_URL + "/" + code;
    return this._http.get(url);
  }

  getTickersByQuery(query: string) {
    return this._http.get(this.SEARCH_API_URL+query);
  }

  getTotalCount() {
    return this._http.get(this.COUNT_API_URL);
  }

  getAlphaMap() {
    return this._http.get(this.ALPHA_MAP_API);
  }

  getAlphaList(alphabet: string) {
    return this._http.get(this.ALPHA_LIST_API+alphabet);
  }
}
