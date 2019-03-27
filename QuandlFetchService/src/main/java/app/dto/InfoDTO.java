package app.dto;

import java.util.Date;

import app.util.TickerErrors;

public class InfoDTO {

	private String quandlCode;
	
	private String name;
	private Date date;
	private Double open, high, low, close, wap, noOfShares, noOfTrades, totalTurnover, deliverableQuantity,
			perDelQuanToTrade, spreadHighLow, spreadCloseOpen;
	
	private TickerErrors error;
	
	public InfoDTO() {}
	
	public InfoDTO(String name, String quandlCode) {
		this.name = name;
		this.quandlCode = quandlCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuandlCode() {
		return quandlCode;
	}

	public void setQuandlCode(String quandlCode) {
		this.quandlCode = quandlCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}

	public Double getWap() {
		return wap;
	}

	public void setWap(Double wap) {
		this.wap = wap;
	}

	public Double getNoOfShares() {
		return noOfShares;
	}

	public void setNoOfShares(Double noOfShares) {
		this.noOfShares = noOfShares;
	}

	public Double getNoOfTrades() {
		return noOfTrades;
	}

	public void setNoOfTrades(Double noOfTrades) {
		this.noOfTrades = noOfTrades;
	}

	public Double getTotalTurnover() {
		return totalTurnover;
	}

	public void setTotalTurnover(Double totalTurnover) {
		this.totalTurnover = totalTurnover;
	}

	public Double getDeliverableQuantity() {
		return deliverableQuantity;
	}

	public void setDeliverableQuantity(Double deliverableQuantity) {
		this.deliverableQuantity = deliverableQuantity;
	}

	public Double getPerDelQuanToTrade() {
		return perDelQuanToTrade;
	}

	public void setPerDelQuanToTrade(Double perDelQuanToTrade) {
		this.perDelQuanToTrade = perDelQuanToTrade;
	}

	public Double getSpreadHighLow() {
		return spreadHighLow;
	}

	public void setSpreadHighLow(Double spreadHighLow) {
		this.spreadHighLow = spreadHighLow;
	}

	public Double getSpreadCloseOpen() {
		return spreadCloseOpen;
	}

	public void setSpreadCloseOpen(Double spreadCloseOpen) {
		this.spreadCloseOpen = spreadCloseOpen;
	}

	public TickerErrors getError() {
		return error;
	}

	public void setError(TickerErrors error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "QuandlDTO [name=" + name + ", quandlCode=" + quandlCode + ", date=" + date + ", open=" + open
				+ ", high=" + high + ", low=" + low + ", close=" + close + ", wap=" + wap + ", noOfShares=" + noOfShares
				+ ", noOfTrades=" + noOfTrades + ", totalTurnover=" + totalTurnover + ", deliverableQuantity="
				+ deliverableQuantity + ", perDelQuanToTrade=" + perDelQuanToTrade + ", spreadHighLow=" + spreadHighLow
				+ ", spreadCloseOpen=" + spreadCloseOpen + ", error=" + error + "]";
	}

}
