package com.xbw.spring.boot.project.repository;

import com.xbw.spring.boot.project.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
