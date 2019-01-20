package com.example.persistenceservice.service.impl;

import com.example.persistenceservice.model.Topic;
import com.example.persistenceservice.repository.TopicRepository;
import com.example.persistenceservice.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.persistenceservice.controller.PersistenceController.LIMIT;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository repository;

    @Autowired
    public TopicServiceImpl(TopicRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Topic> findAll(int page) {
        return repository.findAllByOrderByTopicDateDesc(PageRequest.of(page, LIMIT));
    }

    @Override
    public Long getTopicCount() {
        return repository.count();
    }

    @Override
    public Optional<Topic> findTopicById(String id) {
        return repository.findById(id);
    }

    @Override
    public String saveTopic(Topic topic) {
        return repository.save(topic).getId();
    }

    @Override
    public void deleteTopicById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Topic> searchText(String text) {
        return repository.findByTopicNameLike(text);
    }

}
