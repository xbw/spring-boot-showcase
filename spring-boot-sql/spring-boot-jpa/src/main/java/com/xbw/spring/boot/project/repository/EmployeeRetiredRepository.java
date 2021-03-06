package com.xbw.spring.boot.project.repository;

import com.xbw.spring.boot.project.model.EmployeeRetired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRetiredRepository extends JpaRepository<EmployeeRetired, Long> {
    List<EmployeeRetired> findByWorkerName(String workerName);
}
