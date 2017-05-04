package com.phincon.talents.app.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebMVCconfig extends WebMvcConfigurerAdapter {
	public static final String INDEX_VIEW_NAME = "forward:index.html";
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName(INDEX_VIEW_NAME);
		registry.addViewController("/contact").setViewName(INDEX_VIEW_NAME);

	}
}
