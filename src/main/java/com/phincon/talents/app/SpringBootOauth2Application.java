package com.phincon.talents.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

//@EnableScheduling
//@ComponentScan(basePackages = {"com.phincon.talents.app.async","com.phincon.talents.app.scheduling"})
//@ComponentScan(basePackages = {"com.phincon.talents.app.async"})
@SpringBootApplication
@EnableAsync
@Configuration
public class SpringBootOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOauth2Application.class, args);
    }
    

}
