package com.iftm.serverlogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class ServerLogsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerLogsApplication.class, args);
	}

}
