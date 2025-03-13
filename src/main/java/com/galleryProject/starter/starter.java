package com.galleryProject.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackages = {"com.galleryProject"})
@EntityScan(basePackages = {"com.galleryProject"})
@EnableJpaRepositories(basePackages = {"com.galleryProject"})
@SpringBootApplication
public class starter {

	public static void main(String[] args) {
		SpringApplication.run(starter.class, args);
	}

}
