package com.spring.boot.jpa;

import com.spring.boot.project.model.Employee;
import com.spring.boot.project.model.EmployeeRetired;
import com.spring.boot.project.model.User;
import com.spring.boot.project.repository.EmployeeRepository;
import com.spring.boot.project.repository.EmployeeRetiredRepository;
import com.spring.boot.project.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTests {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeRetiredRepository employeeRetiredRepository;
    @Autowired
    private UserRepository userRepository;


    @Test
    public void testSave() throws Exception {
        employeeRepository.save(new Employee("employee", "employee"));
        employeeRetiredRepository.save(new EmployeeRetired("retired", "retired", "1970-01-01"));
        userRepository.save(new User("user", "user"));
    }


    @Test
    public void testQuery() {
        employeeRepository.findAll();
        employeeRetiredRepository.findAll();
        userRepository.findAll();
    }

    @Test
    public void testDelete() throws Exception {
        employeeRepository.deleteAll();
        employeeRetiredRepository.deleteAll();
        userRepository.deleteAll();
    }

}