package com.tonks.res.webscraper.anuntul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ResWebScraperAnuntulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResWebScraperAnuntulApplication.class, args);
	}

}
