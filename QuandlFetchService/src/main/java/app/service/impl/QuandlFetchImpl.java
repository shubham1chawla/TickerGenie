package app.service.impl;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import app.dto.InfoDTO;
import app.service.QuandlFetch;
import app.util.HttpUtils;
import app.util.QuandlUtils;
import app.util.TickerErrors;

@Service
public class QuandlFetchImpl implements QuandlFetch {
	
	@Value("${config.quandl.api.key}")
	private String QUANDL_API_KEY;
	
	private final String METHOD = "GET";
	
	@Override
	public InfoDTO getQuandlData(String code) throws IOException {
		final String response = HttpUtils.getResponseText(buildQuandlApiUrl(code), METHOD);
		InfoDTO data;
		try {
			data = QuandlUtils.buildDTO(code, null, response);
		} catch (ParseException e) {
			InfoDTO infoDTO = new InfoDTO();
			infoDTO.setError(TickerErrors.DATE_PARSE_ERROR);
			return infoDTO;
		}
		return data;
	}
	
	private String buildQuandlApiUrl(String code) {
		StringBuilder sb = new StringBuilder();
		sb.append("https://www.quandl.com/api/v3/datasets/BSE/");
		sb.append(code);
		sb.append("/data.json?&api_key=");
		sb.append(QUANDL_API_KEY);
		return sb.toString();
	}

}
