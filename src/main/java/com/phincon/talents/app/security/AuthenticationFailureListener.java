package com.phincon.talents.app.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureListener implements
		ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Autowired
	private HttpServletRequest request;

	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
		// WebAuthenticationDetails auth = (WebAuthenticationDetails)
		// e.getAuthentication().getDetails();
		// System.out.println("Auth IP Address " + auth.getRemoteAddress());
		// loginAttemptService.loginFailed(auth.getRemoteAddress());
		System.out.println("Failed Login Here");
		final String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			loginAttemptService.loginFailed(request.getRemoteAddr());
		} else {
			loginAttemptService.loginFailed(xfHeader.split(",")[0]);
		}
	}
	

}