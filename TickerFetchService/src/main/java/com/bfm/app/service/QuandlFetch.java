package com.bfm.app.service;

import java.io.IOException;

import com.bfm.app.dto.InfoDTO;

public interface QuandlFetch {
	public InfoDTO getQuandlData(String code) throws IOException;
}
