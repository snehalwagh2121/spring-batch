package com.example.demo.repo;

import com.example.demo.model.SalesEmployeeCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepositoryCopy extends CrudRepository<SalesEmployeeCopy, String> {
}
