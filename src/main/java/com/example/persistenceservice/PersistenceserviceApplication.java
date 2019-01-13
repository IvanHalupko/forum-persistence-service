package com.example.persistenceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories("com.example.persistenceservice.repository")
public class PersistenceserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersistenceserviceApplication.class, args);
    }

}

