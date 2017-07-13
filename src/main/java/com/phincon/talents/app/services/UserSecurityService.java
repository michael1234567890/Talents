package com.phincon.talents.app.services;

import java.util.Calendar;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.phincon.talents.app.dao.PasswordResetTokenRepository;
import com.phincon.talents.app.model.PasswordResetToken;
import com.phincon.talents.app.model.User;

@Service
@Transactional
public class UserSecurityService {

    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;

    public User getUserFromPasswordResetToken(long id, String token) {
        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
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

        return passToken.getUser();
    }

}