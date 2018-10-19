package com.example.springbootplay.httpclient.impl;

import com.example.springbootplay.data.UserData;
import com.example.springbootplay.httpclient.LocalhostClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * Created by Tony Ng on 18/10/2018.
 */
@Service("localhostClient")
public class LocalhostClientImpl implements LocalhostClient
{
	private Logger LOG = LogManager.getLogger(this.getClass());

	@Autowired
	RestTemplate localhostRestTemplate;

	@Override
	public void checkHealth()
	{
		ResponseEntity<String> responseEntity = localhostRestTemplate.getForEntity("/actuator/health", String.class);
		LOG.info("checkHealth: " + responseEntity.getBody());
	}

	@Override
	public void createUser()
	{
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE + "; charset=UTF-8");
		UserData userData = new UserData();
		userData.setName("hokan");
		userData.setAge(15);
		HttpEntity<UserData> request = new HttpEntity<UserData>(userData, headers);
		ResponseEntity<UserData> response = localhostRestTemplate.postForEntity("/user", request, UserData.class);
		LOG.info("createUser: " + response);
	}
}
