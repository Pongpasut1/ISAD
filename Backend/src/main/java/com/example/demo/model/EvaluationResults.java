package com.example.demo.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "evaResults")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationResults {

    @Id
    private String id;

    private String resultID;

    private String empId;

    private String criteriaId;

    private float KPI_score;

    private float ability_score;

    private float attendance_score;

    private float total_score;

    private String comment;

    private Map<String, Integer> scores;

    //getter
    public String  getId() {
        return id;
    }

    public String getResultID() {
        return resultID;
    }

    public String getEmp_id() {
        return empId;
    }

    public String getCriteriaId() {
        return criteriaId;
    }

    public float getKPI_score() {
        return KPI_score;
    }

    public float getAbility_score() {
        return ability_score;
    }

    public float getAttendance_score() {
        return attendance_score;
    }

    public float getTotal_score() {
        return total_score;
    }

    public String getComment() {
        return comment;
    }

    //setter

    public void setId(String  id) {
        this.id = id;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setResultID(String resultID) {
        this.resultID = resultID;
    }

    public void setCriteriaId(String criteriaId) {
        this.criteriaId = criteriaId;
    }

    public void setKPI_score(float KPI_score) {
        this.KPI_score = KPI_score;
    }

    public void setAbility_score(float ability_score) {
        this.ability_score = ability_score;
    }

    public void setAttendance_score(float attendance_score) {
        this.attendance_score = attendance_score;
    }

    public void setTotal_score(float total_score) {
        this.total_score = total_score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setScores(Map<String, Integer> scores) {
        this.scores = scores;
    }

    public void setTotalScore(float total_score) {
        this.total_score = total_score;
    }
}
