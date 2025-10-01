package com.gkfcsolution.authorizationjwt.repository;


import com.gkfcsolution.authorizationjwt.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 2025 at 10:59
 * File: null.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 01/10/2025
 * @time 10:59
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
