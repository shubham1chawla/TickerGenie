package app;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class QuandlFetchService {
	public static void main(String[] args) {
		new SpringApplicationBuilder(QuandlFetchService.class).web(WebApplicationType.SERVLET).run(args);
	}
}
