package com.phincon.talents.app.services;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.PasswordResetTokenRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.ResetPasswordDTO;
import com.phincon.talents.app.dto.UserChangePasswordDTO;
import com.phincon.talents.app.model.PasswordResetToken;
import com.phincon.talents.app.model.User;

@Service
public class PasswordResetTokenService {
	
	@Autowired
	PasswordResetTokenRepository passwordResetRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	

	@Autowired
	private Environment env;
	
	
	@Transactional
	public void save(PasswordResetToken obj){
		passwordResetRepository.save(obj);
	}
	
	public void actionResetPassword(ResetPasswordDTO resetPassword, PasswordResetToken passwordResetToken){
		User user = passwordResetToken.getUser();
		UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO();
		userChangePasswordDTO.setNewPassword(resetPassword.getNewPassword());
		userChangePasswordDTO.setConfirmPassword(resetPassword.getConfirmPassword());
		userService.changePassword(userChangePasswordDTO, user, false);
		passwordResetToken.setActive(false);
		save(passwordResetToken);
	}
	
	
	public PasswordResetToken findByTokenAndActive(long id, String token) {
        final PasswordResetToken passToken = passwordResetRepository.findByTokenAndActive(token,true);
        if ((passToken == null) || (passToken.getUser()
            .getId() != id)) {
            return null;
        }

        final Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate()
            .getTime() - cal.getTime()
            .getTime()) <= 0) {
            return null;
        }

        return passToken;
    }
	
	public void createPasswordResetTokenForUser(User user, String token) {
		String expired = env.getProperty("talents.resetpassword.expiredmin"); // in minutes
	    PasswordResetToken myToken = new PasswordResetToken(token, user, Integer.valueOf(expired));
	    myToken.setActive(true);
	    myToken.setToday(new Date());
	    save(myToken);
	}

	
}
