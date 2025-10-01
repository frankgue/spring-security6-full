package com.gkfcsolution.springsecurity6full.service.security;

import com.gkfcsolution.springsecurity6full.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2025 at 12:39
 * File: null.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 01/10/2025
 * @time 12:39
 */
public class CustomUserDetails implements UserDetails {
    private String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> roles;

    public CustomUserDetails(User user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.roles = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole_name())).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
//        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
//        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
//        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
