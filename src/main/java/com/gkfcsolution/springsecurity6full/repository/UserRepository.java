package com.gkfcsolution.springsecurity6full.repository;

import com.gkfcsolution.springsecurity6full.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 2025 at 10:40
 * File: null.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 01/10/2025
 * @time 10:40
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
}
