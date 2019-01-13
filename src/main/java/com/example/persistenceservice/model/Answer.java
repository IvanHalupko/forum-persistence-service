package com.example.persistenceservice.model;

import lombok.Data;

@Data
public class Answer {

    private String textAnswer;
    private Long dateAnswer;
    private String userId;
}
