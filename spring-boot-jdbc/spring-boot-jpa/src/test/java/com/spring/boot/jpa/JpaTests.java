package com.spring.boot.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.spring.boot.project.model.Employee;
import com.spring.boot.project.model.EmployeeRetired;
import com.spring.boot.project.model.User;
import com.spring.boot.project.repository.EmployeeRepository;
import com.spring.boot.project.repository.EmployeeRetiredRepository;
import com.spring.boot.project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class JpaTests {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeRetiredRepository employeeRetiredRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void testDelete() {
        employeeRepository.deleteAll();
        employeeRetiredRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testSave() {
        employeeRepository.save(new Employee("employee", "employee"));
        employeeRetiredRepository.save(new EmployeeRetired("retired", "retired", "1970-01-01"));
        userRepository.save(new User("user", "user"));
    }

    @Test
    void testPage() throws JsonProcessingException {
        int num = 10;
        List<User> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(new User("user" + i, "user" + i));
        }
        userRepository.saveAll(list);

        Pageable pageable = PageRequest.of(0, num / 3);
        Page<User> page = userRepository.findAll(pageable);
        System.out.println(new JsonMapper().writeValueAsString(page));
    }

    @Test
    void testQuery() {
        employeeRepository.findAll();
        employeeRetiredRepository.findAll();
        userRepository.findAll();
    }

}