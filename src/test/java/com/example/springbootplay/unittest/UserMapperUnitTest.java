package com.example.springbootplay.unittest;


import com.example.springbootplay.BaseIntegrationTest;
import com.example.springbootplay.dao.UserDao;
import com.example.springbootplay.model.UserModel;
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
	private UserDao userDao;

	private UserModel mockUserA;
	private UserModel mockUserB;

	@Before
	public void before() throws Exception
	{
		mockUserA = new UserModel();
		mockUserA.setName("cyliu");
		mockUserA.setAge(30);

		mockUserB = new UserModel();
		mockUserB.setName("hokan");
		mockUserB.setAge(15);

		userDao.insert(mockUserA);
		userDao.insert(mockUserB);
	}

	@Test
	public void testFindUserById()
	{
		UserModel findUser = userDao.selectById(mockUserA.getId());

		Assert.assertEquals(findUser.getName(), mockUserA.getName());
		Assert.assertEquals(findUser.getAge(), mockUserA.getAge());
	}

	@Test
	public void testInsertUser()
	{
		UserModel user = new UserModel();
		user.setAge(15);
		user.setName("cyliu");
		int id = userDao.insert(user);

		Assert.assertNotNull(id);
	}

	@Test
	public void testGetAllUser()
	{
		List<UserModel> users = userDao.selectList(null);

		Assert.assertEquals("testUserMapperGetAllUser in correct user size", 2, users.size());
		Assert.assertEquals(mockUserA.getName(), users.get(0).getName());
		Assert.assertEquals(mockUserB.getName(), users.get(1).getName());
	}

	@After
	public void after()
	{
	}
}
