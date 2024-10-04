package com.example.demo.repository;

import com.example.demo.model.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CriteriaRepo extends MongoRepository<Criteria, Integer> {
    Criteria findByRole(String role);

    Criteria findByCriteriaId(String criteriaId);
}
