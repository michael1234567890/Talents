package com.phincon.talents.app.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {

		/*auth.userDetailsService(userDetailsService).passwordEncoder(
				passwordEncoder());*/
		// auth.userDetailsService(userDetailsService);
		auth.userDetailsService(customUserDetailsService()).passwordEncoder(
				passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers("/h2console/**")
				.antMatchers("/api/register").antMatchers("/api/activate")
				.antMatchers("/api/lostpassword")
				.antMatchers("/api/resetpassword")
				.antMatchers("/api/actionresetpassword")
				.antMatchers("/api/hello");

	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		//return super.authenticationManagerBean();
		List<AuthenticationProvider> authenticationProviderList = new ArrayList<AuthenticationProvider>();
        authenticationProviderList.add(customAuthenticationProvider());
        
        ProviderManager authenticationManager = new ProviderManager(authenticationProviderList);
        authenticationManager.setAuthenticationEventPublisher(defaultAuthenticationEventPublisher());
        return authenticationManager;
	}

	@Bean
	DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher() {
		return new DefaultAuthenticationEventPublisher();
	}

	@Bean
	AuthenticationProvider customAuthenticationProvider() {
		DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
		impl.setUserDetailsService(customUserDetailsService());
		impl.setPasswordEncoder(passwordEncoder());
		/* other properties etc */
		return impl;
	}


	@Bean
	UserDetailsService customUserDetailsService() {
		return new com.phincon.talents.app.security.UserDetailsService();
	}

	@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
	private static class GlobalSecurityConfiguration extends
			GlobalMethodSecurityConfiguration {
		@Override
		protected MethodSecurityExpressionHandler createExpressionHandler() {
			return new OAuth2MethodSecurityExpressionHandler();
		}

	}

}
