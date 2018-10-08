package com.example.springbootplay.controllertest;

import com.example.springbootplay.BaseControllerIntegrationTest;
import com.example.springbootplay.mapper.UserMapper;
import com.example.springbootplay.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Tony Ng on 6/10/2018.
 * This is integration test, i.e load full env with bean, service, dao, jdbc
 */

// @Transactional should not use in controller test as db session and control session in two different thread, finally controller will get nothing
// ref: https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html#boot-features-testing-spring-boot-applications
public class UserControllerTest extends BaseControllerIntegrationTest
{
	private User mockUserA;
	private User mockUserB;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void before()
	{
		// if mock data is complex, create data sql and load when setup
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
	public void testGetAllUser()
	{
		ResponseEntity<List<User>> response = restTemplate.exchange(
				"/user",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<User>>(){});
		List<User> users = response.getBody();

		Assert.assertEquals("testGetAllUser in correct user size", 2, users.size());
		Assert.assertEquals(mockUserA.getName(), users.get(0).getName());
		Assert.assertEquals(mockUserB.getName(), users.get(1).getName());
	}

	@Test
	public void testExample()
	{

		ResponseEntity<String> responseEntity =
				restTemplate.getForEntity("/user/helloWorld", String.class);
		String string = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Hellow World!", string);
	}

	@After
	public void after()
	{
		userMapper.delete(mockUserA.getId());
		userMapper.delete(mockUserB.getId());
	}
}
