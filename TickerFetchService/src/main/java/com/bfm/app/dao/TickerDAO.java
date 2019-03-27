package com.bfm.app.dao;

import java.util.List;
import java.util.Map;

import com.bfm.app.entity.Ticker;

public interface TickerDAO {
	public Ticker getTickerByCode(String code);
	public List<Ticker> getTickers(Integer startIndex, Integer count);
	public List<Ticker> getTickersLike(String query);
	public Long getTickersCount();
	List<String> getTickerCodes();
	public List<Ticker> getTickersStartingWith(String alpha);
	public Map<String, Long> getStartToCount();
}
