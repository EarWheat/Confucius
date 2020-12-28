package com.education.confucius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.pangu")
@ComponentScan(value = "com.education")
public class ConfuciusApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfuciusApplication.class, args);
	}

}
