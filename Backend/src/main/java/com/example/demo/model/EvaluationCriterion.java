package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EvaluationCriterion {
    private String criterionId;
    private String description;
    private int maxScore;
    private float weight;
    private String type; // "KPI", "Ability", "Attendance"

}

