package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repo.TaskletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskletService {

    @Autowired
    TaskletRepository taskletRepository;

    public void insertInDb(List<Employee> employeeList) {
        taskletRepository.saveAll(employeeList);
    }
}
