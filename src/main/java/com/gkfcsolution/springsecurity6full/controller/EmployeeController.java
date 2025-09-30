package com.gkfcsolution.springsecurity6full.controller;

import com.gkfcsolution.springsecurity6full.entity.Employee;
import com.gkfcsolution.springsecurity6full.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created on 2025 at 16:18
 * File: null.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 30/09/2025
 * @time 16:18
 */
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/fetchAll")
    public List<Employee> fetchAllEmployee(){
      return   employeeService.fetchAllEmployee();
    }
}
