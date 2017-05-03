package com.phincon.talents.app.services;

import java.util.List;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.EmployeeRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.UserChangePasswordDTO;
import com.phincon.talents.app.dto.UserInfoDTO;
import com.phincon.talents.app.model.User;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class UserService   {
	private static final Pattern PASSWORD_REGEX = Pattern
			.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}");

	private static final Pattern EMAIL_REGEX = Pattern
			.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Transactional
	public User findByUsername(String username) {
		return userRepository.findByUsernameCaseInsensitive(username);
	}
	
	@Transactional
	public List<User> findAllUser() {
		return userRepository.findAll();
	}

	@Transactional
	public void createUser(UserInfoDTO userDto) {		
		
		User user = new User();
		user.setEmail(userDto.getEmail()); 
		user.setUsername(userDto.getEmail());
		user.setPassword(userDto.getPassword()); 
		user.setActivated(true);
		// Employee employee = employeeRepository.findOne(userDto.getEmployeeId());
		user.setEmployee(userDto.getEmployeeId());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setCompany(userDto.getCompany());
		userRepository.save(user);
	}
	
	public User findByEmail(String email){
		return userRepository.findByEmail(email);
	}
	

	@Transactional
	public void changePassword(UserChangePasswordDTO userChangePassword, User user) {
		// check old password is match
		if(!userChangePassword.getOldPassword().equals(user.getPassword())) {
			throw new RuntimeException("Your Old Password is wrong.");
		}
		if(!userChangePassword.getNewPassword().equals(userChangePassword.getConfirmPassword())) {
			throw new RuntimeException("Your new password & Confirm Password does not match.");
		}
		if(user.getPassword().equals(userChangePassword.getNewPassword())) {
			throw new RuntimeException("Your new password must be different");
		}
		user.setPassword(userChangePassword.getNewPassword());
		userRepository.save(user);
	}
}
