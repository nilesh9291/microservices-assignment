package com.rest.useraggregate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

//@EnableJpaRepositories(basePackages = {"com.rest"})
//@EntityScan(basePackages = {"com.rest"})
//@ComponentScan(basePackages = {"com.rest"})
public class UserAggregateApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserAggregateApplication.class, args);
    }
}
