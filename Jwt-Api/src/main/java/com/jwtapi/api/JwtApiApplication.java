package com.jwtapi.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.jwtapi", "com.jwtapi.common" })
@EntityScan(basePackages = "com.jwtapi.model")
@EnableJpaRepositories(basePackages = { "com.jwtapi.repository" })
@EnableAspectJAutoProxy
public class JwtApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApiApplication.class, args);
	}

}
