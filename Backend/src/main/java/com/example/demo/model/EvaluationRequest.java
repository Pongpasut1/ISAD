package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class EvaluationRequest {
    // Getter and Setter for employeeId
    private String employeeId;
    // Getter and Setter for criteriaId
    private String criteriaId;
    // Getter and Setter for scores
    private Map<String, Integer> scores;
    // Getter and Setter for comment
    private String comment;

    // Constructor
    public EvaluationRequest() {}

}
