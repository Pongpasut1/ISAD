package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "articles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    //setter
    //getter
    @Id
    private Integer id;

    private String articleId;

    private String description; //คำอธิบายคำถาม

    private float weight; //ค่าน้ำหนักคะแนน(0%-100%)

    private int score; //คะแนนเต็มของข้อนี้

    private String type; //KPI score, คะแนนความสามารถ, other

}
