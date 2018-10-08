package com.example.springbootplay.unittest;


import com.example.springbootplay.BaseIntegrationTest;
import com.example.springbootplay.mapper.UserMapper;
import com.example.springbootplay.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Tony Ng on 6/10/2018.
 */
@Transactional
public class UserMapperUnitTest extends BaseIntegrationTest
{

	@Autowired
	private UserMapper userMapper;

	private User mockUserA;
	private User mockUserB;

	@Before
	public void before() throws Exception
	{
		mockUserA = new User();
		mockUserA.setName("cyliu");
		mockUserA.setAge(30);

		mockUserB = new User();
		mockUserB.setName("hokan");
		mockUserB.setAge(15);

		userMapper.insert(mockUserA);
		userMapper.insert(mockUserB);
	}

	@Test
	public void testFindUserById()
	{
		User findUser = userMapper.findById(mockUserA.getId());

		Assert.assertEquals(findUser.getName(), mockUserA.getName());
		Assert.assertEquals(findUser.getAge(), mockUserA.getAge());
	}

	@Test
	public void testInsertUser()
	{
		User user = new User();
		user.setAge(15);
		user.setName("cyliu");
		long id = userMapper.insert(user);

		Assert.assertNotNull(id);
	}

	@Test
	public void testGetAllUser()
	{
		List<User> users = userMapper.findAll();

		Assert.assertEquals("testUserMapperGetAllUser in correct user size", 2, users.size());
		Assert.assertEquals(mockUserA.getName(), users.get(0).getName());
		Assert.assertEquals(mockUserB.getName(), users.get(1).getName());
	}

	@After
	public void after()
	{
	}
}
