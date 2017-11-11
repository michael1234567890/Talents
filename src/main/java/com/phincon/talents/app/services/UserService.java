package com.phincon.talents.app.services;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.CompanySettingsRepository;
import com.phincon.talents.app.dao.EmployeeRepository;
import com.phincon.talents.app.dao.PasswordResetTokenRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.UserChangePasswordDTO;
import com.phincon.talents.app.dto.UserInfoDTO;
import com.phincon.talents.app.model.CompanySettings;
import com.phincon.talents.app.model.PasswordResetToken;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.utils.PasswordValidator;

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
	private PasswordEncoder passwordEncoder;
	

	@Autowired
	private CompanySettingsRepository companySettingsRepository;
	
	@Autowired
	private PasswordResetTokenRepository passwordTokenRepository;
	
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
		// user.setPassword(userDto.getPassword()); 
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setActivated(true);
		// Employee employee = employeeRepository.findOne(userDto.getEmployeeId());
		user.setEmployee(userDto.getEmployeeId());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setCompany(userDto.getCompany());
		user.setEmployeeExtId(userDto.getEmployeeExtId());
		user.setFullName(userDto.getFullName());
		user.setIsAdmin(userDto.isAdmin());
		user.setIsLeader(userDto.isLeader());
		userRepository.save(user);
	}
	
	public User findByEmail(String email){
		return userRepository.findByEmail(email);
	}
	

	@Transactional
	public void changePassword(UserChangePasswordDTO userChangePassword, User user, boolean checkValidOldPassword) {

		if(checkValidOldPassword){
			if (!passwordEncoder.matches(userChangePassword.getOldPassword(),
	                user.getPassword())) {
				throw new RuntimeException("Your Old Password is wrong.");
			}
		}
		
		
		if(!userChangePassword.getNewPassword().equals(userChangePassword.getConfirmPassword())) {
			throw new RuntimeException("Your new password & Confirm Password does not match.");
		}
		if(user.getPassword().equals(userChangePassword.getNewPassword())) {
			throw new RuntimeException("Your new password must be different");
		}
		
		// Load company Settings
    	List<CompanySettings> listCompanySettings = companySettingsRepository.findByCompany(user.getCompany());
    	CompanySettings companySettings = null;
    	if(listCompanySettings != null && listCompanySettings.size() > 0) {
    		companySettings = listCompanySettings.get(0);
    		// checking password policy (regex)
    		if(companySettings.getIsRegexPasswordActive() && companySettings.getRegexPassword() != null && !companySettings.getRegexPassword().equals("")) {
    			PasswordValidator passwordValidator = new PasswordValidator(companySettings.getRegexPassword());
    			if(!passwordValidator.validate(userChangePassword.getNewPassword())){
    				throw new RuntimeException("Your new password must contains " + companySettings.getMsgErrorRegexPassword());
    			}
    		}
    	}
    	
		user.setPassword(passwordEncoder.encode(userChangePassword.getNewPassword()));
		user.setModifiedDate(new Date());
		user.setModifiedBy(user.getUsername());
		user.setIsChangePassword(true);
		userRepository.save(user);
	}
	
	public void createPasswordResetTokenForUser(User user, String token) {
	    PasswordResetToken myToken = new PasswordResetToken(token, user);
	    passwordTokenRepository.save(myToken);
	}
}
