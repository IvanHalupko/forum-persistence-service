package com.example.persistenceservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "data_collection")
@Data
public class Topic {

	@Id
	private String id;
	private String topicName;
	private Long topicDate;
	private String userId;
	private String category;
	private String userName;
	private List<Answer> answers;

}
