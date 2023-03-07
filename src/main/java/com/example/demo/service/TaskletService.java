package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repo.TaskletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskletService {

    @Autowired
    TaskletRepository taskletRepository;

    public void insertInDb(List<Employee> employeeList) {
        taskletRepository.saveAll(employeeList);
    }

    public List<Employee> getEmployeesFromDB() {
        List<Employee> employeeList = new ArrayList<>();
        taskletRepository.findAll().forEach(e -> employeeList.add(e));
        return employeeList;
    }
}
