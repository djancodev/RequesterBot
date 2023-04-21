package com.gianco.RequesterBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RequesterBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequesterBotApplication.class, args);
	}

}
