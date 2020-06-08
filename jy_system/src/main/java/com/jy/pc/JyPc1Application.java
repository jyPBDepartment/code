package com.jy.pc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@SpringBootApplication
@EnableSpringConfigured
@EnableConfigurationProperties
public class JyPc1Application {

	public static void main(String[] args) {
		SpringApplication.run(JyPc1Application.class, args);
	}

}
