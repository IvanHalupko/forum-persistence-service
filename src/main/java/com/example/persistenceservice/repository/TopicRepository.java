package com.example.persistenceservice.repository;

import com.example.persistenceservice.model.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends MongoRepository<Topic, String> {
    List<Topic> findAllByOrderByTopicDateDesc();
    Optional<Topic> findByTopicNameLike(String search);
}
