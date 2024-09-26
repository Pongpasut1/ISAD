package com.example.demo.Repository;

import com.example.demo.Model.Employees;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeesRepo extends MongoRepository<Employees, Integer> {
    Employees findByUsername(String username);
}
