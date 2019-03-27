package com.bfm.app.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bfm.app.dao.InfoCacheDAO;
import com.bfm.app.dto.InfoDTO;

@Repository
@Transactional
public class InfoCacheDAOImpl implements InfoCacheDAO {

	Map<String, InfoDTO> cache;
	
	@PersistenceContext
	private EntityManager em;
	
	private Boolean isUpdatingDB = false;
	
	@PostConstruct
	public void setup() {
		this.cache = new ConcurrentHashMap<>();
	}
	
	@Override
	public InfoDTO getInfoByCode(String code) {
		return cache.get(code);
	}

	@Override
	public void insertInfo(InfoDTO info) {
		this.cache.put(info.getQuandlCode(), info);
	}

	@Override
	public Map<String, Double> getTickerSharesMap() {
		Map<String, Double> tickerShares = new HashMap<>();
		for(Map.Entry<String, InfoDTO> entry: this.cache.entrySet()) {
			tickerShares.put(entry.getKey(), entry.getValue().getNoOfShares());
		}
		return tickerShares;
	}

	public Boolean isUpdatingDB() {
		return isUpdatingDB;
	}

	public void setIsUpdatingDB(Boolean isUpdatingDB) {
		this.isUpdatingDB = isUpdatingDB;
	}
}
