package com.example.springbootplay.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by Tony Ng on 11/10/2018.
 */
@Component
@ConfigurationProperties(prefix = "customize.test")
public class CustomizeProperties extends BaseProperties
{

	private String key;
	private String value;
	private List<String> name;

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public List<String> getName()
	{
		return name;
	}

	public void setName(List<String> name)
	{
		this.name = name;
	}
}
