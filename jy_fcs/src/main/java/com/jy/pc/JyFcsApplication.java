package com.jy.pc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@SpringBootApplication
@EnableSpringConfigured
public class JyFcsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JyFcsApplication.class, args);
	}
	
}
