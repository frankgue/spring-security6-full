package com.gkfcsolution.springsecurity6full.controller;

import com.gkfcsolution.springsecurity6full.entity.User;
import com.gkfcsolution.springsecurity6full.entity.dto.UserInformation;
import com.gkfcsolution.springsecurity6full.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2025 at 10:42
 * File: null.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 01/10/2025
 * @time 10:42
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public UserInformation findUserDetails(@RequestParam String userName){
        return userService.fetchUser(userName);
    }
}
