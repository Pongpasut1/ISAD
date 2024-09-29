package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "selfEvaResults")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class SelfEvaluationResults {
    @Id
    private Integer id;

    private String self_resultID;

    private String emp_id;

    private Criteria evaluation_cri;

    private float KPI_score;

    private float ability_score;

    private float attendance_score;

    private float total_score;

    private String comment;

    //getter
    public Integer getId() {
        return id;
    }

    public String getSelf_resultID() {
        return self_resultID;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public Criteria getEvaluation_cri() {
        return evaluation_cri;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public void setSelf_resultID(String resultID) {
        this.self_resultID = resultID;
    }

    public void setEvaluation_cri(Criteria evaluation_cri) {
        this.evaluation_cri = evaluation_cri;
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
}
