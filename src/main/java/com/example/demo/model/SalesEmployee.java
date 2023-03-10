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
public class SalesEmployee {

    private String name;
    private String joining_date;
    @Id
    private String email_addr;
    private String dept;
    private String monthly_salary;
    private String job_status;

    public SalesEmployee name(String name) {
        this.name = name;
        return this;
    }

    public SalesEmployee joining_date(String joining_date) {
        this.joining_date = joining_date;
        return this;
    }

    public SalesEmployee email_addr(String email_addr) {
        this.email_addr = email_addr;
        return this;
    }

    public SalesEmployee dept(String dept) {
        this.dept = dept;
        return this;
    }

    public SalesEmployee monthly_salary(String monthly_salary) {
        this.monthly_salary = monthly_salary;
        return this;
    }

    public SalesEmployee job_status(String job_status) {
        this.job_status = job_status;
        return this;
    }

}
