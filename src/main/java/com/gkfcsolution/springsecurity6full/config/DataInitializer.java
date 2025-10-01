package com.gkfcsolution.springsecurity6full.config;

import com.gkfcsolution.springsecurity6full.entity.Role;
import com.gkfcsolution.springsecurity6full.entity.User;
import com.gkfcsolution.springsecurity6full.repository.RoleRepository;
import com.gkfcsolution.springsecurity6full.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 2025 at 10:58
 * File: null.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 01/10/2025
 * @time 10:58
 */
@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    private final PasswordEncoder passwordEncoder;


    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.count() == 0) {
                // 1. Sauvegarde des rôles
                Role roleUser = roleRepository.save(Role.builder().role_name("ROLE_USER").build());
                Role roleAdmin = roleRepository.save(Role.builder().role_name("ROLE_ADMIN").build());
                Role roleEmployee = roleRepository.save(Role.builder().role_name("ROLE_EMPLOYEE").build());

                // 2. Créer les utilisateurs avec des rôles déjà managés
                User admin = User.builder()
                        .userName("admin")
                        .password(passwordEncoder.encode("123456"))
                        .active(true)
                        .roles(List.of(roleAdmin, roleUser))
                        .build();

                User user = User.builder()
                        .userName("user")
                        .password(passwordEncoder.encode("123456"))
                        .active(true)
                        .roles(List.of(roleUser))
                        .build();

                User employee = User.builder()
                        .userName("employee")
                        .password(passwordEncoder.encode("123456"))
                        .active(true)
                        .roles(List.of(roleUser, roleEmployee))
                        .build();

                // 3. Sauvegarder les utilisateurs
                userRepository.saveAll(Arrays.asList(admin, user, employee));

                System.out.println("✅ Database initialized with users and roles");
            }
        };
    }
}
