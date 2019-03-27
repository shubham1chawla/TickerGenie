package app.util;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TickerErrors {

	UNEXPECTED_EXCEPTION(1000, "Unknown error occured, Please contact Support."),
	RESPONSE_EXCEPTION(1001, "Error occured while fetching information from Quandl Servers."),
	DATE_PARSE_ERROR(1002, "Error parsing date while creating Ticker Info DTO."),
	EMPTY_CODE_ERROR(1003, "Code not passed while fetching the data."),
	QUANDL_SERVICE_UNAVAILABLE(1004, "Quandl Service is unavailable, Please contact Support.");

	private Integer code;
	private String message;

	private TickerErrors(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
