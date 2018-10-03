package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Tony Ng on 3/10/2018.
 */
@RestController
public class UserController {

	@Value("${test}")
	private  String name;

	@Autowired
	private Environment env;

	@RequestMapping("/")
	public String hello(){
		String property = env.getProperty("profile");
		System.out.println("property   " + property);
		return name;
	}
}