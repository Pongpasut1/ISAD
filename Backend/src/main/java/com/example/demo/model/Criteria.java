package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "criterias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Criteria {

    private List<EvaluationCriterion> evaluationCriteria;

    @Id
    private String id;

    private String criteriaId;

    private String description;

    private String[] articlesId;

    private String department; //ของแผนกไหน

    private String role; //ของตำแหน่งไหน

    private int KPI_weight; //ค่าน้ำหนักคะแนนKPI(0%-100%)

    private int ability_weight; //ค่าน้ำหนักคะแนนความสามารถ

    private int attendance_weight; //ค่าน้ำหนักคะแนนขาด ลา มาสาย

    //getter
    public String getId(){
        return id;
    }

    public String getCriteriaId(){
        return criteriaId;
    }

    public String getDescription(){
        return description;
    }

    public String[] getArticlesId(){
        return articlesId;
    }

    public String getDepartment(){
        return department;
    }

    public int getKPI_weight() {
        return KPI_weight;
    }

    public int getAbility_weight() {
        return ability_weight;
    }

    public int getAttendance_weight() {
        return attendance_weight;
    }

    public String getRole() {
        return role;
    }

    public List<EvaluationCriterion> getEvaluationCriteria() {
        return evaluationCriteria;
    }


    //setter
    public void setId(String id){
        this.id = id;
    }

    public void setCriteriaId(String criteriaId) {
        this.criteriaId = criteriaId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setArticlesId(String[] articlesId){
        this.articlesId = articlesId;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public void setKPI_weight(int KPI_weight) {
        this.KPI_weight = KPI_weight;
    }

    public void setAttendance_weight(int attendance_weight) {
        this.attendance_weight = attendance_weight;
    }

    public void setAbility_weight(int ability_weight) {
        this.ability_weight = ability_weight;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
