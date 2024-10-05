package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
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

    private LocalDate startDate;
    private LocalDate endDate;

    // Constructor
    public EvaluationRequest() {}

}
