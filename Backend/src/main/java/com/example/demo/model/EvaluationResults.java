package com.example.demo.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Setter
@Document(collection = "evaResults")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationResults {

    //getter
    @Getter
    @Id
    private String id;

    @Getter
    private String resultID;

    private String empId;

    @Getter
    private String criteriaId;

    @Getter
    private float KPI_score;

    @Getter
    private float ability_score;

    @Getter
    private float attendance_score;

    @Getter
    private float total_score;

    @Getter
    private String comment;

    private Map<String, Integer> scores;

    public String getEmp_id() {
        return empId;
    }

    //setter

    public void setTotalScore(float total_score) {
        this.total_score = total_score;
    }
}
