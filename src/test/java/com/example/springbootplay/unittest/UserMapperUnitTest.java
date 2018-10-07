package com.example.springbootplay.unittest;

import com.example.springbootplay.BaseIntegrationTest;
import com.example.springbootplay.mapper.UserMapper;
import com.example.springbootplay.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by Tony Ng on 6/10/2018.
 */
//@Transactional // do not persist any data created during test
public class UserMapperUnitTest extends BaseIntegrationTest
{
	private Long mockUserId;

	@Autowired
	UserMapper userMapper;

	@Before
	public void setUp()
	{
		User user = new User();
		user.setAge(29);
		user.setName("hokan");
		mockUserId = userMapper.insert(user);
	}

	@Test
	public void testFindUserById()
	{
		User user = userMapper.findById(mockUserId);;

		Assert.assertEquals(29, user.getAge().intValue());
		Assert.assertNotNull("hokan", user.getName());
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
}
