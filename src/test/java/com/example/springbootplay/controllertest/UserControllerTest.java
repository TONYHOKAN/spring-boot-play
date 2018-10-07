package com.example.springbootplay.controllertest;

import com.example.springbootplay.BaseControllerIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

/**
 * Created by Tony Ng on 6/10/2018.
 * This is integration test, i.e load full env with bean, service, dao, jdbc
 */
public class UserControllerTest extends BaseControllerIntegrationTest
{
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testExample() throws Exception {

		ResponseEntity<String> responseEntity =
				restTemplate.getForEntity("/user/helloWorld", String.class);
		String string = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Hellow World!", string);
	}
}
