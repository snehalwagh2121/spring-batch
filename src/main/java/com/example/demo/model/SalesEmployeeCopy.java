package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "salesPerson")
public class SalesEmployeeCopy {

    private String name;
    private String joining_date;
    @Id
    private String email_addr;
    private String dept;
    private String monthly_salary;
    private String job_status;

}
