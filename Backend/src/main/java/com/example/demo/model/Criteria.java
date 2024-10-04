package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Document(collection = "criterias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Criteria {

    private List<EvaluationCriterion> evaluationCriteria;

    //setter
    //getter
    @Setter
    @Id
    private String id;

    @Setter
    private String criteriaId;

    @Setter
    private String description;

    @Setter
    private String[] articlesId;

    @Setter
    private String department; //ของแผนกไหน

    @Setter
    private String role; //ของตำแหน่งไหน

    @Setter
    private int KPI_weight; //ค่าน้ำหนักคะแนนKPI(0%-100%)

    @Setter
    private int ability_weight; //ค่าน้ำหนักคะแนนความสามารถ

    @Setter
    private int attendance_weight; //ค่าน้ำหนักคะแนนขาด ลา มาสาย


}
