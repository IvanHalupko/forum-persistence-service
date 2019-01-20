package com.example.persistenceservice.service;

import com.example.persistenceservice.model.Answer;
import com.example.persistenceservice.model.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicService {

    List<Topic> findAll(int page);
    Long getTopicCount();
    Optional<Topic> findTopicById(String id);
    String saveTopic(Topic topic);
    void deleteTopicById(String id);
    Optional<Topic> searchText(String text);
}
