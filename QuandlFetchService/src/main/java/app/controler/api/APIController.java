package app.controler.api;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import javax.naming.ServiceUnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import app.dto.InfoDTO;
import app.service.QuandlFetch;
import app.util.TickerErrors;

@RestController
public class APIController {

	@Autowired
	private QuandlFetch fetch;
	
	@GetMapping("/quandl/{code}")
	public ResponseEntity<String> retrieveQuandlData(@PathVariable(value = "code", required = false) String code) {
		InfoDTO quandlData = new InfoDTO();
		Gson g = new Gson();
		if(StringUtils.isEmpty(code)) {
			quandlData.setError(TickerErrors.EMPTY_CODE_ERROR);
			return new ResponseEntity<>(g.toJson(quandlData), HttpStatus.OK);
		}
	
		try {
			quandlData = fetch.getQuandlData(code);
			return new ResponseEntity<>(g.toJson(quandlData), HttpStatus.OK);
		} catch (IOException e) {
			quandlData.setError(TickerErrors.UNEXPECTED_EXCEPTION);
			return new ResponseEntity<>(g.toJson(quandlData), HttpStatus.OK);
		}
	}	
	
	/*
	 * ALL METHODS BELOW ARE FOR CONSUL DISCOVERY
	 * 
	 */

	@Autowired
	private DiscoveryClient discoveryClient;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	public Optional<URI> serviceUrl() {
		return discoveryClient.getInstances("quandl-fetch-service").stream().findFirst().map(si -> si.getUri());
	}
	
	@GetMapping("/discoveryClient")
	public String discoveryRetrieveQuandlData() throws ServiceUnavailableException {
		URI service = serviceUrl().map(s -> s.resolve("/quandl/{code}")).orElseThrow(ServiceUnavailableException::new);
		return restTemplate.getForEntity(service, String.class).getBody();
	}
	
}
