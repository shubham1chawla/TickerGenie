package com.bfm.app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bfm.app.dao.InfoCacheDAO;
import com.bfm.app.dao.TickerDAO;
import com.bfm.app.dto.InfoDTO;
import com.bfm.app.service.CacheManager;
import com.bfm.app.service.QuandlFetch;

@Service
public class CacheManagerImpl implements CacheManager {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Value("${config.cache.interval}")
	private long CACHING_INTERVAL;
	
	private Boolean caching = false;
	
	@Autowired
	private QuandlFetch fetch;
	
	@Autowired
	private TickerDAO tickerDao;
	
	@Autowired
	private InfoCacheDAO cache;
	
	private List<String> tickerCodes;
	
	@PostConstruct
	public void setup() {
		this.tickerCodes = tickerDao.getTickerCodes();
	}
	
	@Override
	public void startCachingService() {
		new Timer().scheduleAtFixedRate(new TimerTask(){
			
		    @Override
		    public void run(){
		    	
		       log.info("Started Caching Process.");
		       setCaching(true);
		       
		       InfoDTO info;
		       Integer num = 0;
		       for(String code: getTickerCodes()) {
		    	   try {
		    		   info = getFetch().getQuandlData(code);
		    	   } catch (IOException e) {
		    		   info = null;
		    	   }
		    	   getCache().insertInfo(info);
		    	   num++;
		    	   
		    	   if(num%250 == 0) {
		    		   try {
		    			   log.info("Cached "+num+" recoreds.");
		    			   Thread.sleep(5000);
		    		   } catch (InterruptedException e) {
		    			   log.warn("Thread failed to sleep");
		    		   }
		    	   }
		       }
		       
		       log.info("Caching Complete");
		       setCaching(false);
		    }
		},0,CACHING_INTERVAL);
	}

	public Boolean isCaching() {
		return caching;
	}

	public void setCaching(Boolean caching) {
		this.caching = caching;
	}

	public List<String> getTickerCodes() {
		return tickerCodes;
	}

	public QuandlFetch getFetch() {
		return fetch;
	}

	public InfoCacheDAO getCache() {
		return cache;
	}
		
}
