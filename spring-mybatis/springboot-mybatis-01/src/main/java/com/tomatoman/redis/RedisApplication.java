package com.tomatoman.redis;

import com.tomatoman.redis.entity.Role;
import com.tomatoman.redis.service.RoleService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableTransactionManagement//开启事务注解
@SpringBootApplication
@MapperScan("com.tomatoman.redis.mapper")
public class RedisApplication {
	@Autowired
	private RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

	@ResponseBody
	@RequestMapping("hello")
	public String hello() {
		Role role = roleService.selectByPrimaryKey(1);
		System.out.println(role);

		return "dddd";
	}
}
