package com.gkfcsolution.authorizationjwt.filter;

import com.gkfcsolution.authorizationjwt.service.security.CustomUserDetailsService;
import com.gkfcsolution.authorizationjwt.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Created on 2025 at 16:45
 * File: null.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 01/10/2025
 * @time 16:45
 */
@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeaderValue = request.getHeader("Authorization");
        String token = null;
        String userName = null;
        Claims claims = null;

        if (authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer ")) {

            token = authorizationHeaderValue.substring(7);
            try {
                 claims = jwtUtils.extractAllClaims(token);
                userName = claims.getSubject();
            } catch (Exception e) {
                logger.warn("JWT invalide ou expiré: " + e.getMessage());
            }

            /* try {
                token = authorizationHeaderValue.substring(7);
                userName = jwtUtils.extractUsername(token);
            }catch (Exception e) {
                logger.warn("JWT invalide ou expiré: " + e.getMessage());
            }*/
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);

            if (jwtUtils.isTokenValid(token, userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }


            String refreshToken = jwtUtils.generateAccessToken(userDetails, claims);

            System.out.println("refreshToken => " + refreshToken);
        }

        filterChain.doFilter(request, response);

    }
}
