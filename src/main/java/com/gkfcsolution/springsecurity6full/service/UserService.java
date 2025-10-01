package com.gkfcsolution.springsecurity6full.service;

import com.gkfcsolution.springsecurity6full.entity.User;
import com.gkfcsolution.springsecurity6full.entity.dto.UserInformation;
import com.gkfcsolution.springsecurity6full.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2025 at 10:41
 * File: null.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 01/10/2025
 * @time 10:41
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public UserInformation fetchUser(String userName){
        User user =  userRepository.findByUserName(userName);
        return UserInformation.fromUser(user);
    }
}
