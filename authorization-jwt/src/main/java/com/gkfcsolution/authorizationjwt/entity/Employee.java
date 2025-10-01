package com.gkfcsolution.authorizationjwt.entity;

import lombok.*;

/**
 * Created on 2025 at 16:50
 * File: Employee.java.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 30/09/2025
 * @time 16:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Employee {
    private int id;
    private String name;
}
