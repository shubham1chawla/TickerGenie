package com.bfm.app.controller.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bfm.app.dao.TickerDAO;
import com.bfm.app.dto.InfoDTO;
import com.bfm.app.entity.Ticker;
import com.bfm.app.service.QuandlFetch;
import com.bfm.app.util.TickerErrors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TickerAPIController {

	private final Integer DEFAULT_START_ROW = 0;
	private final Integer DEFAULT_COUNT_ROW = 15;
	
	@Autowired
	private TickerDAO tickerDAO;
	
	@Autowired
	@Qualifier("qunadl-via-consul")
	private QuandlFetch quandlFetch;
	
	@GetMapping("/tickers")
	public ResponseEntity<List<Ticker>> getTickers(@RequestParam(value = "start", required = false) Integer start,
												   @RequestParam(value = "count", required = false) Integer count) {
		
		List<Ticker> tickers = tickerDAO.getTickers((start != null) ? start : DEFAULT_START_ROW, (count != null) ? count : DEFAULT_COUNT_ROW);
		return new ResponseEntity<>(tickers, HttpStatus.OK);
	}
	
	@GetMapping("/tickers/search")
	public ResponseEntity<List<Ticker>> getTickersViaQuery(@RequestParam(value = "query", required = false) String query) {
		if(StringUtils.isEmpty(query)) {
			return this.getTickers(DEFAULT_START_ROW, DEFAULT_COUNT_ROW);
		}
		List<Ticker> tickers = tickerDAO.getTickersLike(query);
		return new ResponseEntity<>(tickers, HttpStatus.OK);
	}
	
	@GetMapping("/tickers/{code}")
	public ResponseEntity<InfoDTO> getTickerInfo(@PathVariable(value = "code", required = false) String code) {
		InfoDTO quandlData = new InfoDTO();	
		if(StringUtils.isEmpty(code)) {
			quandlData.setError(TickerErrors.EMPTY_CODE_ERROR);
			return new ResponseEntity<>(new InfoDTO(), HttpStatus.OK);
		}
	
		try {
			quandlData = quandlFetch.getQuandlData(code);
			return new ResponseEntity<>(quandlData, HttpStatus.OK);
		} catch (IOException e) {
			quandlData.setError(TickerErrors.UNEXPECTED_EXCEPTION);
			return new ResponseEntity<>(quandlData, HttpStatus.OK);
		}
	}
	
	@GetMapping("/tickers/count")
	public ResponseEntity<Long> getTickersCount() {
		Long count = tickerDAO.getTickersCount();
		return new ResponseEntity<>(count, HttpStatus.OK);
	}
	
	@GetMapping("/tickers/alpha/{alpha}")
	public ResponseEntity<List<Ticker>> getTickersStartingWith(@PathVariable("alpha") String alpha) {
		List<Ticker> tickers = tickerDAO.getTickersStartingWith(alpha);
		if(CollectionUtils.isEmpty(tickers)) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
		}
		return new ResponseEntity<>(tickers, HttpStatus.OK);
	}
	
	@GetMapping("/tickers/map/alpha")
	public ResponseEntity<Map<String, Long>> getTickerAlphaCounts() {
		return new ResponseEntity<>(tickerDAO.getStartToCount(), HttpStatus.OK);
	}
	
	@GetMapping("/tickers/map/shares")
	public ResponseEntity<Map<String, Long>> getTickerShares() {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
