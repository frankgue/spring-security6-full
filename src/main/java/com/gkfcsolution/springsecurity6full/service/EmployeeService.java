package com.gkfcsolution.springsecurity6full.service;

import com.gkfcsolution.springsecurity6full.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2025 at 16:52
 * File: EmployeeService.java.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 30/09/2025
 * @time 16:52
 */
@Service
public class EmployeeService {
    public List<Employee> fetchAllEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(Employee.builder().id(1).name("Frank").build());
        employeeList.add(Employee.builder().id(2).name("Cabrel").build());

        return employeeList;
    }

    public Employee getEmployee(int id) {
        return new Employee(1, "GUEKENG");
    }
}
