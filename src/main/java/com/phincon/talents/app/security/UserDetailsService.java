package com.phincon.talents.app.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.phincon.talents.app.config.CustomException;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.model.Role;
import com.phincon.talents.app.model.User;

@Component("userDetailsService")
public class UserDetailsService implements
		org.springframework.security.core.userdetails.UserDetailsService {

	private final Logger log = LoggerFactory
			.getLogger(UserDetailsService.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String login) {

		log.debug("Authenticating {}", login);
		String lowercaseLogin = login.toLowerCase();

		String ip = getClientIP();
		if (loginAttemptService.isBlocked(ip)) {
			throw new CustomException("Your account has been blocked !!!");
		}

		User userFromDatabase;
		if (lowercaseLogin.contains("@")) {
			userFromDatabase = userRepository.findByEmail(lowercaseLogin);
		} else {
			userFromDatabase = userRepository
					.findByUsernameCaseInsensitive(lowercaseLogin);
		}

		if (userFromDatabase == null) {
			throw new UsernameNotFoundException("User " + lowercaseLogin
					+ " was not found in the database");
		} else if (!userFromDatabase.isActivated()) {
			throw new UserNotActivatedException("User " + lowercaseLogin
					+ " is not activated");
		}

		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (Role authority : userFromDatabase.getRoles()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
					authority.getName());
			grantedAuthorities.add(grantedAuthority);
		}
		
		return new org.springframework.security.core.userdetails.User(
				userFromDatabase.getUsername(), userFromDatabase.getPassword(),
				grantedAuthorities);
		// return new
		// org.springframework.security.core.userdetails.User(userFromDatabase.getUsername(),
		// Utils.encrypt(userFromDatabase.getPassword()), grantedAuthorities);

	}

	public UserDetailsService() {
		super();
	}

	private String getClientIP() {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}

}
