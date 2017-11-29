package com.phincon.talents.app.dao;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.phincon.talents.app.model.PasswordResetToken;
import com.phincon.talents.app.model.User;
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	
    PasswordResetToken findByToken(String token);
    PasswordResetToken findByTokenAndActive(String token,Boolean active);
    
    PasswordResetToken findByUser(User user);
    
    List<PasswordResetToken> findByUserAndToday(User user,Date date);
    
    
    Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);
    
    void deleteByExpiryDateLessThan(Date now);
    
    @Modifying
    @Query("delete from PasswordResetToken t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
}
