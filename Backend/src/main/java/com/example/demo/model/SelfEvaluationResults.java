package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "selfEvaResults")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class SelfEvaluationResults {
    //getter
    @Id
    private Integer id;

    private String self_resultID;

    private String emp_id;

    private String criteriaId;

    private float KPI_score;

    private float ability_score;

    private float attendance_score;

    private float total_score;

    private String comment;

    //setter

}
