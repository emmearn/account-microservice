package com.exercise.demomicroservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoMicroserviceApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DemoMicroserviceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoMicroserviceApplication.class, args);
	}

	@Override
	public void run(String... string) throws Exception {
		log.info("Hello world!");
	}

}
