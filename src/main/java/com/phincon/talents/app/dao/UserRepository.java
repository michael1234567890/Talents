package com.phincon.talents.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.phincon.talents.app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username)")
    User findByUsernameCaseInsensitive(@Param("username") String username);

    @Query
    User findByEmail(String email);
    
    @Query
    User findByEmployee(Long employee);

    @Query
    User findByEmailAndActivationKey(String email, String activationKey);

    @Query
    User findByEmailAndResetPasswordKey(String email, String resetPasswordKey);
    
    

}