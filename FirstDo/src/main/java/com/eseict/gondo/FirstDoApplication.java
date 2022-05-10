package com.eseict.gondo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class FirstDoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstDoApplication.class, args);
	}

}
