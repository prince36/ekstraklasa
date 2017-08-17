package com.ekstraklasa.football.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.builder.SpringApplicationBuilder;



@Configuration
@SpringBootApplication
@ComponentScan(basePackages = {"com.ekstraklasa.football", "com.ekstraklasa.football.parser", "com.ekstraklasa.football.repo"})
@EnableJpaRepositories(basePackages = "com.ekstraklasa.football.repo")
@EntityScan(basePackages = "com.ekstraklasa.football.model")
@EnableAutoConfiguration
@EnableScheduling
public class FootballApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FootballApplication.class);
		//return null;
	}
	public static void main(String[] args) {
		SpringApplication.run(FootballApplication.class, args);

	}
}
