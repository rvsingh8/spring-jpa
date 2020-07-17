package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		ContactRepository repository = context.getBean(ContactRepository.class);
		/*
		 * repository.save(new Contact("John", "first@test.com")); repository.save(new
		 * Contact("Rohn", "second@test.com"));
		 */
	}

}
