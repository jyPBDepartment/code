package com.jy.pc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@SpringBootApplication
@EnableSpringConfigured
@EnableConfigurationProperties
public class JyEduApplication {

	public static void main(String[] args) {
		SpringApplication.run(JyEduApplication.class, args);
	}

}
