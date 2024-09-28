package com.example.demo.Model;

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

    private String article_id;

    private String description;

    private float weight;

    private int score;

    //getter
    public Integer getId(){
        return id;
    }

    public String getArticle_id(){
        return article_id;
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

    //setter
    public void setId(Integer id){
        this.id = id;
    }

    public void setArticle_id(String article_id){
        this.article_id = article_id;
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
}
