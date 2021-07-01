package com.spring.boot.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.spring.boot.project.model.primary.Employee;
import com.spring.boot.project.model.primary.EmployeeRetired;
import com.spring.boot.project.model.secondary.User;
import com.spring.boot.project.repository.primary.EmployeeRetiredRepository;
import com.spring.boot.project.repository.secondary.UserRepository;
import com.spring.boot.project.repository.tertiary.EmployeeRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JpaMultiTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeRetiredRepository employeeRetiredRepository;
    @Autowired
    private UserRepository userRepository;

    void printJSON(Object o) {
        try {
            logger.info(new JsonMapper().writeValueAsString(o));
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException -> ", e);
        }
    }

    @Test
    @Order(2)
    void delete() {
        employeeRepository.deleteAll();
        employeeRetiredRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @Order(3)
    void save() {
        printJSON(employeeRepository.save(new Employee("employee", "employee")));
        printJSON(employeeRetiredRepository.save(new EmployeeRetired("retired", "retired", "1970-01-01")));
        printJSON(userRepository.save(new User("user", "user")));
    }

    @Test
    @Order(1)
    void page() {
        int num = 10;
        List<User> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(new User("user" + i, "user" + i));
        }
        printJSON(userRepository.saveAll(list));

        Pageable pageable = PageRequest.of(0, num / 3);
        Page<User> page = userRepository.findAll(pageable);
        printJSON(page);
    }

    @Test
    @Order(4)
    void query() {
        printJSON(employeeRepository.findAll());
        printJSON(employeeRetiredRepository.findAll());
        printJSON(userRepository.findAll());
    }


}