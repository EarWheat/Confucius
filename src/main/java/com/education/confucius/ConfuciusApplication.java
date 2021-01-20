package com.education.confucius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.pangu")
@ComponentScan(value = "com.education")
public class ConfuciusApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(ConfuciusApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(ConfuciusApplication.class);
		springApplication.run(args);
	}

}
