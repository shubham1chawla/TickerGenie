package com.bfm.app.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InfoCacheEntity {

	@Id
	@GeneratedValue
	private Integer id;

	private String name, quandlCode;
	private Date date, cachedDate;
	private Double open, high, low, close, wap, noOfShares, noOfTrades, totalTurnover, deliverableQuantity,
			perDelQuanToTrade, spreadHighLow, spreadCloseOpen;

	public InfoCacheEntity() {
	}

	public InfoCacheEntity(String name, String quandlCode, Date date, Date cachedDate, Double open, Double high,
			Double low, Double close, Double wap, Double noOfShares, Double noOfTrades, Double totalTurnover,
			Double deliverableQuantity, Double perDelQuanToTrade, Double spreadHighLow, Double spreadCloseOpen) {
		this.name = name;
		this.quandlCode = quandlCode;
		this.date = date;
		this.cachedDate = cachedDate;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.wap = wap;
		this.noOfShares = noOfShares;
		this.noOfTrades = noOfTrades;
		this.totalTurnover = totalTurnover;
		this.deliverableQuantity = deliverableQuantity;
		this.perDelQuanToTrade = perDelQuanToTrade;
		this.spreadHighLow = spreadHighLow;
		this.spreadCloseOpen = spreadCloseOpen;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getCachedDate() {
		return cachedDate;
	}

	public void setCachedDate(Date cachedDate) {
		this.cachedDate = cachedDate;
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

}
