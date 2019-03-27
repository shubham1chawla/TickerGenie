package com.bfm.app.util;

import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Component;

@Component
public class SanitizeUtils {
	
	public static String sanatizeInput(String unsanatizeInput) {
		return Jsoup.clean(StringEscapeUtils.escapeHtml4(unsanatizeInput), Whitelist.none());
	}
}