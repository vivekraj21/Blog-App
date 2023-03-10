package com.masai.springngblog;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.masai.springngblog.config.SwaggerConfiguration;

@SpringBootApplication
@Import(SwaggerConfiguration.class)
public class SpringngblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringngblogApplication.class, args);
	}

}
