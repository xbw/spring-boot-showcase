package com.xbw.spring.boot.project.repository.tertiary;

import com.xbw.spring.boot.project.model.primary.EmployeeRetired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("employeeRetiredRepositoryTertiary")
public interface EmployeeRetiredRepository extends JpaRepository<EmployeeRetired, Long> {
    List<EmployeeRetired> findByWorkerName(String workerName);
}
