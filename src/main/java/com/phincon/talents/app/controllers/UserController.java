package com.phincon.talents.app.controllers;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.phincon.talents.app.dto.UserInfoDTO;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger LOGGER = Logger.getLogger(UserController.class
			.getName());

	@Autowired
	UserService userService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public UserInfoDTO getUserInfo(Principal principal) {
		// User user = userService.findByUsername(principal.getName());
		String username = "hendra";
		User user = userService.findByUsername(username);
		// return user != null ? new UserInfoDTO(user.getUsername()) : null;
		return user != null ? new UserInfoDTO(username) : null;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void createUser(@RequestBody UserInfoDTO user) {
		userService.createUser(user);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> errorHandler(Exception exc) {
		LOGGER.error(exc.getMessage(), exc);
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
