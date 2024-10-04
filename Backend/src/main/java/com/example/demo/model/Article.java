package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    private Integer id;

    private String articleId;

    private String description; //คำอธิบายคำถาม

    private float weight; //ค่าน้ำหนักคะแนน(0%-100%)

    private int score; //คะแนนเต็มของข้อนี้

    private String type; //KPI score, คะแนนความสามารถ, other

    //getter
    public Integer getId(){
        return id;
    }

    public String getArticleId(){
        return articleId;
    }

    public String getDescription(){
        return description;
    }

    public float getWeight(){
        return weight;
    }

    public int getScore(){
        return score;
    }

    public String getType(){
        return type;
    }

    //setter
    public void setId(Integer id){
        this.id = id;
    }

    public void setArticleId(String articleId){
        this.articleId = articleId;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setWeight(float weight){
        this.weight = weight;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void setType(String type){
        this.type = type;
    }
}
