package com.example.demo.Service;

// Counter model class
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "counters")
public class Counter {
    @Id
    private String id;
    private int seq;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getSeq() { return seq; }
    public void setSeq(int seq) { this.seq = seq; }
}
