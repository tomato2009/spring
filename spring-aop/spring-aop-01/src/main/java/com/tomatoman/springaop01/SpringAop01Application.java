package com.tomatoman.springaop01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class SpringAop01Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringAop01Application.class, args);
	}
}
