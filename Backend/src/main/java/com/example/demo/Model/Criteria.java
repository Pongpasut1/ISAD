package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "criterias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Criteria {
    @Id
    private Integer id;

    private String criteria_id;

    private String description;

    private Article[] articles;

    private String department; //ของแผนกไหน

    private String role; //ของตำแหน่งไหน

    private int KPI_weight; //ค่าน้ำหนักคะแนนKPI(0%-100%)

    private int ability_weight; //ค่าน้ำหนักคะแนนความสามารถ

    private int attendance_weight; //ค่าน้ำหนักคะแนนขาด ลา มาสาย

    //getter
    public Integer getId(){
        return id;
    }

    public String getCriteria_id(){
        return criteria_id;
    }

    public String getDescription(){
        return description;
    }

    public Article[] getArticles(){
        return articles;
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

    //setter
    public void setId(Integer id){
        this.id = id;
    }

    public void setCriteria_id(String criteria_id) {
        this.criteria_id = criteria_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setArticles(Article[] articles){
        this.articles = articles;
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
