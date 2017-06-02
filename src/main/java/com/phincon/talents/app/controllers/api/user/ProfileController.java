package com.phincon.talents.app.controllers.api.user;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.ProfileDTO;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.utils.CustomMessage;
import com.phincon.talents.app.utils.Utils;

@RestController
@RequestMapping("api")
public class ProfileController {
	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/user/profile/changeprofile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> changePhotoProfile(
			@RequestBody ProfileDTO request, OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		if (request.getImage() == null) {
			throw new RuntimeException("Image can't empty");
		}
		String pathname = "profile/" + RandomStringUtils.randomAlphanumeric(10)
				+ "." + Utils.UPLOAD_IMAGE_TYPE;
		Utils.createImage(request.getImage(), pathname);
		user.setPhotoProfile(pathname);
		userRepository.save(user);
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Photo Profile successfully to save", false), HttpStatus.OK);
	}

}
