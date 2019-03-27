package app.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import app.dto.InfoDTO;

@Component
public class QuandlUtils {
	
	public static InfoDTO buildDTO(String code, String name, String response) throws ParseException {
		
		InfoDTO data = new InfoDTO();
		data.setQuandlCode(code);
		
		// Sets name from the database mapped to the quandl code
		data.setName(name);
		
		JsonParser parser = new JsonParser();
		
		// Getting the main json object
		JsonElement jsonTree = parser.parse(response);
		JsonObject jsonObj = jsonTree.getAsJsonObject();
		
		// Extracting the "dataset_data" object
		JsonElement dataset_data = jsonObj.get("dataset_data");
		JsonObject dataset_data_obj = dataset_data.getAsJsonObject();
		
		// Extracting the data relevent data from json
		JsonElement data_in_dataset = dataset_data_obj.get("data");
		JsonArray data_in_dataset_array = data_in_dataset.getAsJsonArray();
		
		// Extracting the most recent element in the record
		JsonElement first_record_element = null;
		try {
			first_record_element = data_in_dataset_array.get(0);
		}
		catch(Exception e) {
			
			// This error occuered when there is no error thrown by Quandl itself.			
			data.setError(TickerErrors.RESPONSE_EXCEPTION);
			return data;
		}
		JsonArray first_record_array = first_record_element.getAsJsonArray();
	
		// Extracted the last date of updation
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		data.setDate(df.parse(first_record_array.get(0).getAsString()));
		
		// Extracting rest of the fields
		data.setOpen(nullSafeDoubleFromJSON(first_record_array, 1));
		data.setHigh(nullSafeDoubleFromJSON(first_record_array, 2));
		data.setLow(nullSafeDoubleFromJSON(first_record_array, 3));
		data.setClose(nullSafeDoubleFromJSON(first_record_array, 4));
		data.setWap(nullSafeDoubleFromJSON(first_record_array, 5));
		data.setNoOfShares(nullSafeDoubleFromJSON(first_record_array, 6));
		data.setNoOfTrades(nullSafeDoubleFromJSON(first_record_array, 7));
		data.setTotalTurnover(nullSafeDoubleFromJSON(first_record_array, 8));
		data.setDeliverableQuantity(nullSafeDoubleFromJSON(first_record_array, 9));
		data.setPerDelQuanToTrade(nullSafeDoubleFromJSON(first_record_array, 10));
		data.setSpreadHighLow(nullSafeDoubleFromJSON(first_record_array, 11));
		data.setSpreadCloseOpen(nullSafeDoubleFromJSON(first_record_array, 12));
		
		data.setError(null);
		return data;
	}
	
	private static Double nullSafeDoubleFromJSON(JsonArray array, Integer index) {
		if(array != null && array.size() > index && !array.get(index).isJsonNull()) {
			return array.get(index).getAsDouble();
		}
		return null;
	}
	
}
