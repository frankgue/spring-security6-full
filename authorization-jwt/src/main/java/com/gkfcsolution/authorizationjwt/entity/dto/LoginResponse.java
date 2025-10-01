package com.gkfcsolution.authorizationjwt.entity.dto;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * Created on 2025 at 13:52
 * File: null.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 01/10/2025
 * @time 13:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Component
public class LoginResponse {
//    private String jwt;
    private String token;
}
