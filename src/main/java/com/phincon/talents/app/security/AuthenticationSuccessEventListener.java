package com.phincon.talents.app.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.model.User;

@Component
public class AuthenticationSuccessEventListener implements
		ApplicationListener<AuthenticationSuccessEvent> {
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private LoginAttemptService loginAttemptService;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent e) {
		
		final String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			loginAttemptService.loginSucceeded(request.getRemoteAddr());
		} else {
			loginAttemptService.loginSucceeded(xfHeader.split(",")[0]);
		}
		
	
		
		UserDetails userDetails = (UserDetails) e.getAuthentication().getPrincipal();
		User user = userRepository.findByUsernameCaseInsensitive(userDetails.getUsername());
		user.setLastLoginAddress(request.getRemoteAddr());
		String userAgent = request.getHeader("User-Agent");
		user.setUserAgent(userAgent);
		user.setLastLoginTime(new Date());
		userRepository.save(user);
	}
}
