package com.phincon.talents.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
//@EnableScheduling
//@ComponentScan(basePackages = {"com.phincon.talents.app.async","com.phincon.talents.app.scheduling"})
//@ComponentScan(basePackages = {"com.phincon.talents.app.async"})
@Configuration
public class SpringBootOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOauth2Application.class, args);
    }
}
