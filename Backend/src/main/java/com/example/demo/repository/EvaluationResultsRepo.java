package com.example.demo.repository;

import com.example.demo.model.EvaluationResults;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationResultsRepo extends MongoRepository<EvaluationResults, Long> {
    List<EvaluationResults> findAllByOrderByTotalScoreDesc();
}
