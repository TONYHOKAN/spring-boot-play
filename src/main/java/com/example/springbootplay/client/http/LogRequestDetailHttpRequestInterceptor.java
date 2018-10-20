package com.example.springbootplay.client.http;

import org.apache.commons.io.IOUtils;
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
public class LogRequestDetailHttpRequestInterceptor implements ClientHttpRequestInterceptor
{
	private Logger LOG = LogManager.getLogger(this.getClass());

	@Override
	public ClientHttpResponse intercept(
			HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution) throws IOException
	{
		logRequestDetails(request, body);
		return execution.execute(request, body);
	}

	private void logRequestDetails(HttpRequest request, byte[] body)
	{
		try
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Headers: {}", request.getHeaders());
				LOG.debug("Request Method: {}", request.getMethod());
				LOG.debug("Request URI: {}", request.getURI());
				LOG.debug("Request BODY: {}", IOUtils.toString(body));
			}
		}
		catch (Exception e)
		{
			LOG.error("exception", e);
		}

	}
}
