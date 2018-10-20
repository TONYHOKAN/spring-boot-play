package com.example.springbootplay.data.dto;

import com.example.springbootplay.data.BaseData;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Created by Tony Ng on 18/10/2018.
 */
@XmlRootElement(name = "root")
@XmlType(propOrder = {"id", "name", "age"})
public class UserData extends BaseData
{
	private Long id;
	private String name;
	private Integer age;

	@XmlElement
	@JsonProperty("id")
	public Long getId()
	{
		return id;
	}

	@XmlElement
	@JsonProperty("name")
	public String getName()
	{
		return name;
	}

	@XmlElement
	@JsonProperty("age")
	public Integer getAge()
	{
		return age;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}
}
