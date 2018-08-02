package com.tomatoman.dubbocustomer;

import org.springframework.boot.SpringApplication;

import javax.annotation.PostConstruct;
import com.alibaba.dubbo.config.annotation.Reference;
import com.tomatoman.service.HelloService;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DubboCustomerApplication {

	@Reference(version = "1.0.0")
  	private HelloService demoService;

	public static void main(String[] args) {
		
		SpringApplication.run(DubboCustomerApplication.class, args);
	}
	
    @PostConstruct
    public void init() {
    	String sayHello = demoService.sayHello("world");
    	System.err.println(sayHello);
    }
}
