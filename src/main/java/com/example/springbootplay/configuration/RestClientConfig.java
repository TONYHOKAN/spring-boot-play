package com.example.springbootplay.configuration;

import com.example.springbootplay.httpclient.AddAuthorizationHttpRequestInterceptor;
import com.example.springbootplay.httpclient.LogRequestDetailHttpRequestInterceptor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Tony Ng on 18/10/2018.
 * if need pooling, check: https://howtodoinjava.com/spring-restful/resttemplate-httpclient-java-config/
 * if need ssl, check: https://www.baeldung.com/httpclient-ssl
 */
@Configuration
public class RestClientConfig
{
	private static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = (5 * 1000);
	private static final int DEFAULT_CONNECT_TIMEOUT_MILLISECONDS = (5 * 1000);

	@Value("${local.host.root}")
	String localHostRootPath;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder)
	{
		return builder.build();
	}

	@Bean
	public RestTemplate localhostRestTemplate(RestTemplateBuilder builder)
	{
		RestTemplate restTemplate = builder
				.rootUri(localHostRootPath)
				.build();
		List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors = new ArrayList<>();
		clientHttpRequestInterceptors.add(new AddAuthorizationHttpRequestInterceptor());
		if (CollectionUtils.isNotEmpty(restTemplate.getInterceptors()))
		{
			clientHttpRequestInterceptors.addAll(restTemplate.getInterceptors());
		}
		restTemplate.setInterceptors(clientHttpRequestInterceptors);
		return restTemplate;
	}

	/**
	 * RestTemplateCustomizer will be automatically registered with the auto-configured RestTemplateBuilder and are applied to any templates that are built with it.
	 * ref: https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-resttemplate.html
	 * @return
	 */
	@Bean
	public RestTemplateCustomizer restTemplateCustomizer() {
		return restTemplate -> {
			restTemplate.setRequestFactory(clientHttpRequestFactory());
			restTemplate.getInterceptors().add(new LogRequestDetailHttpRequestInterceptor());
		};
	}

	/**
	 * HttpComponentsClientHttpRequestFactory is implementation using apache httpclient
	 *
	 * @return
	 */
	private ClientHttpRequestFactory clientHttpRequestFactory()
	{
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS);
		factory.setConnectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLISECONDS);
		return factory;
	}


}
