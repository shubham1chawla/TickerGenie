package com.bfm.app.dao;

import java.util.Map;

import com.bfm.app.dto.InfoDTO;

public interface InfoCacheDAO {
	public InfoDTO getInfoByCode(String code);
	public void insertInfo(InfoDTO info);
	public Map<String, Double> getTickerSharesMap();
}
