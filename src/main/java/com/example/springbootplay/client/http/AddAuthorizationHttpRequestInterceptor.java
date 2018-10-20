package com.example.springbootplay.client.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;


/**
 * Created by Tony Ng on 18/10/2018.
 */
public class AddAuthorizationHttpRequestInterceptor implements ClientHttpRequestInterceptor
{
	private Logger LOG = LogManager.getLogger(this.getClass());

	@Override
	public ClientHttpResponse intercept(
			HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution) throws IOException
	{
		request.getHeaders().add("Authorization", "Bearer testToken");
		return execution.execute(request, body);
	}
}
