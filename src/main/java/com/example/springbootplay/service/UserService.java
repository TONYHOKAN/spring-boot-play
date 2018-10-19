package com.example.springbootplay.service;

import com.example.springbootplay.model.User;

import java.util.List;


/**
 * Created by Tony Ng on 19/10/2018.
 */
public interface UserService
{
	long insert(User user);

	long update(User user);

	User findById(Long id);

	long delete(Long id);

	List<User> findAll();
}