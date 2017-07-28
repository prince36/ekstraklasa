/*package com.carshow.carshow.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@Configuration
@SpringBootApplication
@ComponentScan(basePackages = "com.carshow.carshow")
@EnableJpaRepositories(basePackages = "com.carshow.carshow.repo")
@EntityScan(basePackages = "com.carshow.carshow.model")
@EnableAutoConfiguration
public class CarshowApplication extends SpringBootServletInitializer {

	private static Class applicationClass = CarshowApplication.class;


	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CarshowApplication.class, args);

		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			//System.out.println(beanName);
		}
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}


}
//862970*/