package com.example.demo.repo;

import com.example.demo.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskletRepository extends CrudRepository<Employee, String> {
}
