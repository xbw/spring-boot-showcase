package com.spring.boot.jpa;

import com.spring.boot.project.model.primary.Employee;
import com.spring.boot.project.model.primary.EmployeeRetired;
import com.spring.boot.project.model.secondary.User;
import com.spring.boot.project.repository.primary.EmployeeRetiredRepository;
import com.spring.boot.project.repository.secondary.UserRepository;
import com.spring.boot.project.repository.tertiary.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaMultiTests {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeRetiredRepository employeeRetiredRepository;
    @Autowired
    private UserRepository userRepository;


    @Test
    void testDelete() throws Exception {
        employeeRepository.deleteAll();
        employeeRetiredRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testSave() throws Exception {
        employeeRepository.save(new Employee("employee", "employee"));
        employeeRetiredRepository.save(new EmployeeRetired("retired", "retired", "1970-01-01"));
        userRepository.save(new User("user", "user"));
    }


    @Test
    void testQuery() {
        employeeRepository.findAll();
        employeeRetiredRepository.findAll();
        userRepository.findAll();
    }

}