package com.example.demo.repository;

import com.example.demo.model.Employees;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeesRepo extends MongoRepository<Employees, Long> {
    Employees findByUsername(String username);
    Employees findByEmpId(String empId);
}
