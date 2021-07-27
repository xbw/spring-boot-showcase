package com.xbw.spring.boot.project.repository.primary;

import com.xbw.spring.boot.project.model.primary.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
