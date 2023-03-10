package com.example.demo.repo;

import com.example.demo.model.SalesEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<SalesEmployee, String> {
}
