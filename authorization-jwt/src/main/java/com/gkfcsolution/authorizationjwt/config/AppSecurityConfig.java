package com.gkfcsolution.authorizationjwt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gkfcsolution.authorizationjwt.filter.AuthorizationFilter;
import com.gkfcsolution.authorizationjwt.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.asm.TypeReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2025 at 17:08
 * File: null.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 30/09/2025
 * @time 17:08
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AppSecurityConfig {

    private final JwtUtils jwtUtils;
    private final AuthorizationFilter authorizationFilter;

 /*   @Bean
    public InMemoryUserDetailsManager configureAuthentication(){
        List<UserDetails> userDetails = new ArrayList<>();
        List<GrantedAuthority> employeeRoles = new ArrayList<>();
        List<GrantedAuthority> adminRoles = new ArrayList<>();
        List<GrantedAuthority> userRoles = new ArrayList<>();
        employeeRoles.add(new SimpleGrantedAuthority("EMPLOYEE"));
        adminRoles.add(new SimpleGrantedAuthority("ADMIN"));
        userRoles.add(new SimpleGrantedAuthority("USER"));

        userDetails.add(new User("employee", passwordEncoder().encode("123456"), employeeRoles));
        userDetails.add(new User("admin", passwordEncoder().encode("123456"), adminRoles));
        userDetails.add(new User("user", passwordEncoder().encode("123456"), userRoles));

        return new InMemoryUserDetailsManager();
    }*/

    /*  private final UserDetailsService userDetailsService;


      @Bean
      public JwtAuthenticationFilter jwtAuthenticationFilter() {
          return new JwtAuthenticationFilter(jwtUtils, userDetailsService);
      }

      @Bean
      public AuthenticationProvider authenticationProvider() {
          DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
          provider.setUserDetailsService(userDetailsService);
          provider.setPasswordEncoder(passwordEncoder());
          return provider;
      }*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/login").permitAll()
                        .anyRequest().authenticated()

                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();


    }
}
