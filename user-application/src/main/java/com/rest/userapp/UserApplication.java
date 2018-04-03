package com.rest.userapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@EnableJpaRepositories(basePackages = {"com.rest"})
@EntityScan(basePackages = {"com.rest"})
@ComponentScan(basePackages = {"com.rest"})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}

/*@Configuration
class Config {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}*/
