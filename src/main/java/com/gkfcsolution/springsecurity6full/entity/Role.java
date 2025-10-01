package com.gkfcsolution.springsecurity6full.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/**
 * Created on 2025 at 10:34
 * File: null.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 01/10/2025
 * @time 10:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String role_name;
}
