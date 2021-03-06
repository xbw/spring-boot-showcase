package com.xbw.spring.boot.project.controller;

import com.xbw.spring.boot.project.model.primary.Employee;
import com.xbw.spring.boot.project.model.primary.EmployeeRetired;
import com.xbw.spring.boot.project.model.secondary.User;
import com.xbw.spring.boot.project.repository.primary.EmployeeRetiredRepository;
import com.xbw.spring.boot.project.repository.secondary.UserRepository;
import com.xbw.spring.boot.project.repository.tertiary.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class JpaMultiController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeRetiredRepository employeeRetiredRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("employee/add")
    public Employee addEmployee() {
        return employeeRepository.save(new Employee("employee", "employee"));
    }

    @RequestMapping("employee/query/{id}")
    public Employee queryEmployee(@PathVariable Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @RequestMapping("employee/query")
    public List<Employee> queryEmployee() {
        return employeeRepository.findAll();
    }

    @RequestMapping("retired/add")
    public EmployeeRetired addEmployeeRetired() {
        return employeeRetiredRepository.save(new EmployeeRetired("retired", "retired", "1970-01-01"));
    }

    @RequestMapping("retired/query/{id}")
    public EmployeeRetired queryEmployeeRetired(@PathVariable Long id) {
        Optional<EmployeeRetired> optional = employeeRetiredRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @RequestMapping("retired/query")
    public List<EmployeeRetired> queryEmployeeRetired() {
        return employeeRetiredRepository.findAll();
    }

    @RequestMapping("user/add")
    public User addUser() {
        return userRepository.save(new User("user", "user"));
    }

    @RequestMapping("user/query/{id}")
    public User queryUser(@PathVariable Long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @RequestMapping("user/query")
    public List<User> queryUser() {
        return userRepository.findAll();
    }

}
