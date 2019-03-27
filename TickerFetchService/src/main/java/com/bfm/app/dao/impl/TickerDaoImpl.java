package com.bfm.app.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.bfm.app.dao.TickerDAO;
import com.bfm.app.entity.Ticker;

@Repository
@Transactional
public class TickerDaoImpl implements TickerDAO {

	@PersistenceContext
	private EntityManager em;
	
	private Map<String, Long> alphaMap;
	
	@PostConstruct
	public void setup() {
		this.alphaMap = getDefaultAplhaMap();
	}
	
	@Override
	public List<Ticker> getTickers(Integer startIndex, Integer count) {
		List<Ticker> res = em.createNamedQuery("GET_TICKERS", Ticker.class).setFirstResult(startIndex).setMaxResults(count).getResultList();
		if(CollectionUtils.isEmpty(res)) {
			return new ArrayList<>();
		}
		return res;
	}

	@Override
	public List<Ticker> getTickersLike(String query) {
		List<Ticker> res = em.createNamedQuery("SEARCH_QUERY",Ticker.class).setParameter("query", "%"+query+"%").getResultList();
		if(CollectionUtils.isEmpty(res)) {
			return new ArrayList<>();
		}
		return res;
	}

	@Override
	public Ticker getTickerByCode(String code) {
		return em.createNamedQuery("GET_BY_CODE", Ticker.class).setParameter("code", code).getSingleResult();
	}

	@Override
	public Long getTickersCount() {
		Long res = (Long) em.createQuery("SELECT COUNT(*) FROM Ticker t").getSingleResult();
		if(res != null) {		
			return res;
		}
		return 0l;
	}

	@Override
	public List<Ticker> getTickersStartingWith(String alpha) {
		List<Ticker> res = em.createNamedQuery("SEARCH_QUERY",Ticker.class).setParameter("query", alpha+"%").getResultList();
		if(CollectionUtils.isEmpty(res)) {
			return new ArrayList<>();
		}
		return res;		
	}
	
	@Override
	public List<String> getTickerCodes() {
		List<String> res = new ArrayList<>();
		List<Ticker> list = em.createNamedQuery("GET_TICKERS", Ticker.class).getResultList();
		for(Ticker t: list) {
			res.add(t.getCode());
		}
		return res;
	}

	@Override
	public Map<String, Long> getStartToCount() {
		if(CollectionUtils.isEmpty(this.alphaMap)) {
			return new HashMap<>();
		}
		return this.alphaMap;
	}
	
	private Map<String, Long> getDefaultAplhaMap() {
		Map<String, Long> counts = new HashMap<>();
		
		for (char ch = 'A'; ch <= 'Z'; ++ch) {			
			counts.put(String.valueOf(ch), 0l); 
		}
		
		for(Map.Entry<String, Long> entry: counts.entrySet()) {
			Long count = (Long) em.createQuery("SELECT COUNT(*) FROM Ticker t WHERE t.name LIKE '"+entry.getKey()+"%'")
										.getSingleResult();
			counts.put(entry.getKey(), count);
		}
		
		return counts;
	}

}
