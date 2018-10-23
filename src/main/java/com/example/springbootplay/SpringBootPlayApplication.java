package com.example.springbootplay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;


@SpringBootApplication
@MapperScan("com.example.springbootplay.dao")
public class SpringBootPlayApplication extends SpringBootServletInitializer
{
	@Autowired
	private static Environment env;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(SpringBootPlayApplication.class);
	}

	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootPlayApplication.class, args);
		System.out.println("env " + env);
	}
}
