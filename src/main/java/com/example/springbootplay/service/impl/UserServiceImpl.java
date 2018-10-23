package com.example.springbootplay.service.impl;

import com.example.springbootplay.dao.UserDao;
import com.example.springbootplay.model.UserModel;
import com.example.springbootplay.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Tony Ng on 19/10/2018.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, UserModel> implements UserService
{
	private Logger LOG = LogManager.getLogger(this.getClass());

	@Autowired
	UserDao userDao;

	@Override
	public long insert(UserModel userModel)
	{
		return userDao.insert(userModel);
	}

	@Override
	public long update(UserModel userModel)
	{
		return userDao.updateById(userModel);
	}

	@Override
	public UserModel findById(Long id)
	{
		return userDao.selectById(id);
	}

	@Override
	public long delete(Long id)
	{
		return userDao.deleteById(id);
	}

	@Override
	public List<UserModel> findAll()
	{
		return userDao.selectList(null);
	}

	@Override
	public UserModel findByName(String name)
	{
		return userDao.findByName(name);
	}
}
