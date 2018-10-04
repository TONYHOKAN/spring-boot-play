package com.example.springbootplay.model;

/**
 * Created by Tony Ng on 4/10/2018.
 */
public class User
{
	private Long id;
	private String name;
	private Integer age;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Integer getAge()
	{
		return age;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}
}
