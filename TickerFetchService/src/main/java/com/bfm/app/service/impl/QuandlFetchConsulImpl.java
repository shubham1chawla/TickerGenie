package com.bfm.app.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bfm.app.dto.InfoDTO;
import com.bfm.app.service.QuandlFetch;
import com.bfm.app.util.TickerErrors;
import com.google.gson.Gson;

@Service
@Qualifier("qunadl-via-consul")
public class QuandlFetchConsulImpl implements QuandlFetch {

	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	@LoadBalanced
	public RestTemplate generateTemplate() {
		return new RestTemplate();
	}
	
	@Override
	public InfoDTO getQuandlData(String code) throws IOException {
		String res;
		InfoDTO infoDTO = new InfoDTO();
		try {
			res = restTemplate.exchange("http://quandl-fetch-service/quandl/{code}", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, code)
	                 .getBody();
			infoDTO = new Gson().fromJson(res, InfoDTO.class);
		} catch(Exception e) {
			infoDTO.setError(TickerErrors.QUANDL_SERVICE_UNAVAILABLE);
		}
		return infoDTO;
	}

}
