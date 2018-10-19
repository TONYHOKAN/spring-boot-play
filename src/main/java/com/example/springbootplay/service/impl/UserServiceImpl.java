package com.example.springbootplay.service.impl;

import com.example.springbootplay.dao.impl.UserDao;
import com.example.springbootplay.model.User;
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
public class UserServiceImpl implements UserService
{
	private Logger LOG = LogManager.getLogger(this.getClass());

	@Autowired
	UserDao userDao;

	@Override
	public long insert(User user)
	{
		return userDao.insert(user);
	}

	@Override
	public long update(User user)
	{
		return userDao.update(user);
	}

	@Override
	public User findById(Long id)
	{
		return userDao.findById(id);
	}

	@Override
	public long delete(Long id)
	{
		return userDao.delete(id);
	}

	@Override
	public List<User> findAll()
	{
		return userDao.findAll();
	}
}
