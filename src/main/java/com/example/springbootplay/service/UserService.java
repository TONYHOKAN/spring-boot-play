package com.example.springbootplay.service;

import com.example.springbootplay.model.UserModel;

import java.util.List;


/**
 * Created by Tony Ng on 19/10/2018.
 */
public interface UserService extends BaseService<UserModel>
{
	long insert(UserModel userModel);

	long update(UserModel userModel);

	UserModel findById(Long id);

	long delete(Long id);

	List<UserModel> findAll();

	UserModel findByName(String name);
}
