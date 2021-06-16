package com.spring.boot.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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
    public void testDelete() throws Exception {
        employeeRepository.deleteAll();
        employeeRetiredRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testSave() throws Exception {
        employeeRepository.save(new Employee("employee", "employee"));
        employeeRetiredRepository.save(new EmployeeRetired("retired", "retired", "1970-01-01"));
        userRepository.save(new User("user", "user"));
    }

    @Test
    public void testPage() throws JsonProcessingException {
        int num = 10;
        List<User> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(new User("user" + i, "user" + i));
        }
        userRepository.saveAll(list);

        Pageable pageable = PageRequest.of(0, num / 3);
        Page<User> page = userRepository.findAll(pageable);
        System.out.println( new JsonMapper().writeValueAsString(list));
    }

    @Test
    public void testQuery() {
        employeeRepository.findAll();
        employeeRetiredRepository.findAll();
        userRepository.findAll();
    }

}