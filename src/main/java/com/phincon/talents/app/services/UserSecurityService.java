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

    
}