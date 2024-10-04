package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EvaluationCriterion {
    private String criterionId;
    private String description;
    private int maxScore;
    private float weight;
    private String type; // "KPI", "Ability", "Attendance"

    public int getMaxScore() {
        return maxScore;
    }

    public float getWeight() {
        return weight;
    }

    public String getCriterionId() {
        return criterionId;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

}

