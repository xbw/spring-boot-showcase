package com.spring.boot.project.repository.tertiary;

import com.spring.boot.project.model.primary.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("employeeRepositoryTertiary")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
