package com.example.springbootplay.dao.impl;

import com.example.springbootplay.mapper.UserMapper;
import com.example.springbootplay.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Tony Ng on 19/10/2018.
 */
@Repository
public class UserDaoImpl implements UserDao
{
	private Logger LOG = LogManager.getLogger(this.getClass());

	@Autowired
	private UserMapper userMapper;

	@Override
	public long insert(User user)
	{
		return userMapper.insert(user);
	}

	@Override
	public long update(User user)
	{
		return userMapper.update(user);
	}

	@Override
	public User findById(Long id)
	{
		return userMapper.findById(id);
	}

	@Override
	public long delete(Long id)
	{
		return userMapper.delete(id);
	}

	@Override
	public List<User> findAll()
	{
		return userMapper.findAll();
	}
}
