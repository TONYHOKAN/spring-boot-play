package com.example.springbootplay.controller;

import com.example.springbootplay.mapper.UserMapper;
import com.example.springbootplay.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Tony Ng on 3/10/2018.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController
{
	@Autowired
	private UserMapper userMapper;

	@RequestMapping("")
	public User getUser()
	{
		User u = userMapper.findByName("cyliu");
		return u;
	}
}