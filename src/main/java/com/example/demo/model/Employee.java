package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    private String name;
    private String joining_date;
    @Id
    private String email_addr;
    private String dept;
    private String monthly_salary;
    private String job_status;

    public Employee name(String name) {
        this.name = name;
        return this;
    }

    public Employee joining_date(String joining_date) {
        this.joining_date = joining_date;
        return this;
    }

    public Employee email_addr(String email_addr) {
        this.email_addr = email_addr;
        return this;
    }

    public Employee dept(String dept) {
        this.dept = dept;
        return this;
    }

    public Employee monthly_salary(String monthly_salary) {
        this.monthly_salary = monthly_salary;
        return this;
    }

    public Employee job_status(String job_status) {
        this.job_status = job_status;
        return this;
    }
}
