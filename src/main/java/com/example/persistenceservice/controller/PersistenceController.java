package com.example.persistenceservice.controller;

import com.example.persistenceservice.model.Answer;
import com.example.persistenceservice.model.Topic;
import com.example.persistenceservice.model.TopicCountWrapper;
import com.example.persistenceservice.service.TopicService;
import com.sun.jersey.api.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PersistenceController {

    public static final int LIMIT = 5;
    private final TopicService topicService;

    @Autowired
    public PersistenceController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/topics/{page}")
    public List<Topic> getTopics(@PathVariable Integer page) {
        return topicService.findAll(page);
    }

    @GetMapping("/topics")
    public List<Topic> getTopics() {
        return topicService.findAll();
    }

    @GetMapping("/topic/{topicId}")
    public Topic getTopicById(@PathVariable("topicId") String topicId) {
        return topicService.findTopicById(topicId)
                .orElseThrow(NotFoundException::new);
    }

    @GetMapping("/topic-count")
    public TopicCountWrapper getTopicCount() {
        return new TopicCountWrapper().withTopicCount(topicService.getTopicCount());
    }

    @PostMapping("/topic")
    public String saveTopic(@Valid @RequestBody Topic topic) {
        return topicService.saveTopic(topic);
    }

    @DeleteMapping("/topic/{topicId}")
    public void deleteTopicById(@PathVariable("topicId") String topicId) {
        topicService.deleteTopicById(topicId);
    }

    @PostMapping("/answer/{topicId}")
    public String saveAnswer(@PathVariable("topicId") String topicId, @Valid @RequestBody Answer answer) {
        Topic topic = topicService.findTopicById(topicId)
                .orElseThrow(NotFoundException::new);

        List<Answer> answers = topic.getAnswers() == null ? new ArrayList<>() : topic.getAnswers();
        answers.add(answer);
        topic.setAnswers(answers);

        return topicService.saveTopic(topic);
    }

    @DeleteMapping("/answer/{topicId}/{userId}/{date}")
    public void deleteAnswerByUserAndDate(
            @PathVariable("topicId") String topicId,
            @PathVariable("userId") String userId,
            @PathVariable("date") Long date) {

        Topic topic = topicService.findTopicById(topicId)
                .orElseThrow(NotFoundException::new);

        List<Answer> answers = topic.getAnswers();
        if (answers == null) {
            return;
        }

        Optional<Answer> foundAnswer = answers.stream()
                .filter(f -> f.getUserId().equals(userId) && f.getDateAnswer().equals(date))
                .findFirst();
        Answer answer = foundAnswer.orElseThrow(NotFoundException::new);
        answers.remove(answer);
        topic.setAnswers(answers);
        topicService.saveTopic(topic);
    }

    @GetMapping("/search/{searchText}")
    public Topic searchTopic(@PathVariable("searchText") String searchText) {
        return topicService.searchText(searchText).orElse(null);
    }
}
