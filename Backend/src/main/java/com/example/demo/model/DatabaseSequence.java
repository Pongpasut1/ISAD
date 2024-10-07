package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "database_sequences")
public class DatabaseSequence {

    @Id
    private String id;

    private long seq;


    public DatabaseSequence() {}

    public DatabaseSequence(String id, long seq) {
        this.id = id;
        this.seq = seq;
    }


}
