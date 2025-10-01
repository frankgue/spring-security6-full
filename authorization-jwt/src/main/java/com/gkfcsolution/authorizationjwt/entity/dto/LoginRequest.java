package com.gkfcsolution.authorizationjwt.entity.dto;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * Created on 2025 at 13:51
 * File: LoginRequest.java.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 01/10/2025
 * @time 13:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Component
public class LoginRequest {
    private String userName;
    private String password;
}
