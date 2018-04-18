package com.rest.aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserAccountsAggreagator {
    public static void main(String[] args) {
        SpringApplication.run(UserAccountsAggreagator.class, args);
    }
}

@Configuration
class Config {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder().build();
	}
}
