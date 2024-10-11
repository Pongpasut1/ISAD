package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Map;

@Setter
@Getter
@Document(collection = "selfevaluation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelfEvaluation {

    public static final String SEQUENCE_NAME = "self_evaluation_sequence";

    @Id
    private Long id;

    private String empId;

    private String criteriaId;

    private float KPI_score;

    private float ability_score;

    private float attendance_score;

    private float total_score;

    private String comment;

    private Map<String, Integer> scores;

    private LocalDate evaluationDate;

}
