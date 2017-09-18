package com.phincon.talents.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
@ComponentScan(basePackages = {"com.phincon.talents.app.async","com.phincon.talents.app.dao"})
public class AppConfig {

}
