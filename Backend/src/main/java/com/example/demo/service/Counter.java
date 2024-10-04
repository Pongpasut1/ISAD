package com.example.demo.service;

// Counter model class
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "counters")
public class Counter {
    // Getters and setters
    @Id
    private String id;
    private int seq;

}
