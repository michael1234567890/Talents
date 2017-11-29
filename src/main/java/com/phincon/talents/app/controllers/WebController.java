package com.phincon.talents.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phincon.talents.app.dto.UserInfoDTO;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.security.SecurityService;
import com.phincon.talents.app.services.UserService;

@Controller
public class WebController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;



	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout, UserInfoDTO userInfo) {
		if (error != null)
			model.addAttribute("error",
					"Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message",
					"You have been logged out successfully.");

		return "login";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginProcess(@ModelAttribute UserInfoDTO userInfo) {
		return "redirect:/welcome"; 
	}
	
	

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String welcome(Model model) {
		return "index";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm,
			BindingResult bindingResult, Model model) {
		// userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		// userService.createUser(userForm);
		 securityService.autologin(userForm.getUsername(), userForm.getConfirmPassword());

		return "redirect:/welcome";
	}
	
	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public String resetPassword(Model model){
		//System.out.println("User ID " + userid + " Token " + token);
		model.addAttribute("token", "aabbccdd");
		model.addAttribute("userId", "34");
		return "change-password";
	}

}
