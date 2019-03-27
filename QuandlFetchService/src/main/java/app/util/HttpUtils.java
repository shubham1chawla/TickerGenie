package app.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class HttpUtils {
	
	public static String getResponseText(String urlString, String method) throws IOException {
		final URL url = new URL(urlString);
		final URLConnection urlConnection = url.openConnection();
		HttpURLConnection http = (HttpURLConnection)urlConnection;
		http.setRequestMethod(method);
		http.connect();
		InputStream inputStream = http.getInputStream();
		Scanner s = new Scanner(inputStream);
		s.useDelimiter("\\A");
		String result = s.hasNext() ? s.next() : "";
		s.close();
		inputStream.close();
		return result;
	}
	
}
