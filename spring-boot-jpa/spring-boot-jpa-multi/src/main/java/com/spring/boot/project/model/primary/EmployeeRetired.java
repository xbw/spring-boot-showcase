package com.spring.boot.project.model.primary;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class EmployeeRetired extends Employee implements Serializable {
    private String retiredDate;

    public EmployeeRetired() {
        super();
    }


    public EmployeeRetired(String workerCode, String workerName, String retiredDate) {
        super(workerCode, workerName);
        this.retiredDate = retiredDate;
    }

    public EmployeeRetired(Long id, String workerCode, String workerName, String retiredDate) {
        super(id, workerCode, workerName);
        this.retiredDate = retiredDate;
    }

    public String getRetiredDate() {
        return retiredDate;
    }

    public void setRetiredDate(String retiredDate) {
        this.retiredDate = retiredDate;
    }
}
