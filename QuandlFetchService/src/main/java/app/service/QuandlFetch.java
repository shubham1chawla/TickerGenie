package app.service;

import java.io.IOException;

import app.dto.InfoDTO;

public interface QuandlFetch {
	public InfoDTO getQuandlData(String code) throws IOException;
}
