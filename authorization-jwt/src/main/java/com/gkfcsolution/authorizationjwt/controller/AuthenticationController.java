package com.gkfcsolution.authorizationjwt.controller;

import com.gkfcsolution.authorizationjwt.entity.dto.LoginRequest;
import com.gkfcsolution.authorizationjwt.entity.dto.LoginResponse;
import com.gkfcsolution.authorizationjwt.service.security.CustomUserDetailsService;
import com.gkfcsolution.authorizationjwt.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created on 2025 at 13:49
 * File: null.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 01/10/2025
 * @time 13:49
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @PostMapping(value = "/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws Exception{
        System.out.println(loginRequest);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        } catch (BadCredentialsException e){
            throw new Exception("Bad Credentials... Invalid user");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserName());
//        String  refreshToken =  jwtUtils.generateRefreshToken(userDetails);
        String  token =  jwtUtils.generateRefreshToken(userDetails);

        return new LoginResponse(token);
    }
}
