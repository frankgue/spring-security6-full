package com.gkfcsolution.springsecurity6full.entity.dto;

import com.gkfcsolution.springsecurity6full.entity.Role;
import com.gkfcsolution.springsecurity6full.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2025 at 12:30
 * File: null.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 01/10/2025
 * @time 12:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserInformation {
    private int id;
    private String userName;
    private boolean active;
    private List<Role> roles = new ArrayList<>();

    public static UserInformation fromUser(User user){
        return UserInformation.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .active(user.isActive())
                .roles(user.getRoles())
                .build();
    }

}
