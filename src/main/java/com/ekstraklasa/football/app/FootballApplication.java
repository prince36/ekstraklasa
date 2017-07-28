package com.ekstraklasa.football.app;

import com.ekstraklasa.football.parser.od_parser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;


@Configuration
@SpringBootApplication
@ComponentScan(basePackages = {"com.ekstraklasa.football", "com.ekstraklasa.football.parser"})
@EnableJpaRepositories(basePackages = "com.ekstraklasa.football.repo")
@EntityScan(basePackages = "com.ekstraklasa.football.model")
@EnableAutoConfiguration
@EnableScheduling
public class FootballApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballApplication.class, args);

	}
}
