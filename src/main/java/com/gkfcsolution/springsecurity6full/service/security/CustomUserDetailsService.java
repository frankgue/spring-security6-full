package com.gkfcsolution.springsecurity6full.service.security;

import com.gkfcsolution.springsecurity6full.entity.User;
import com.gkfcsolution.springsecurity6full.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created on 2025 at 12:37
 * File: null.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 01/10/2025
 * @time 12:37
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        return new CustomUserDetails(user);
    }
}
