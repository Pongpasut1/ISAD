package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeEvaluationDTO {

    private String empId;
    private String name;
    private String surname;
    private String department;
    private float totalScore;

    // Constructors

    public EmployeeEvaluationDTO(String empId, String name, String surname, String department, float totalScore) {
        this.empId = empId;
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.totalScore = totalScore;
    }


}

