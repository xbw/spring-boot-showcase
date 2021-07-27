package com.xbw.spring.boot.project.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "hr_c_employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String workerCode;
    private String workerName;

    public Employee() {
        super();
    }

    public Employee(String workerCode, String workerName) {
        this.workerCode = workerCode;
        this.workerName = workerName;
    }

    public Employee(Long id, String workerCode, String workerName) {
        this.id = id;
        this.workerCode = workerCode;
        this.workerName = workerName;
    }


    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkerCode() {
        return workerCode;
    }

    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }
}
