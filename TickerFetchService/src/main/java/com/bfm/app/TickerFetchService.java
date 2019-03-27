package com.bfm.app;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import com.bfm.app.service.CacheManager;

@SpringBootApplication
@EnableDiscoveryClient
public class TickerFetchService {
	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(TickerFetchService.class).web(WebApplicationType.SERVLET).run(args);
		//CacheManager cacheManager = context.getBean(CacheManager.class);
		//cacheManager.startCachingService();
	}
}
