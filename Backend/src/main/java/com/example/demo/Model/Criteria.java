package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

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

    private String department;

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
}
