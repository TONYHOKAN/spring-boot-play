package com.example.springbootplay.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Created by Tony Ng on 15/10/2018.
 * @JsonPropertyOrder to make sure mapping csv column to correct attribute sequence
 */
@JsonPropertyOrder({"id_with_different key", "Name with different key", "Name with different_KEY"})
public class TestFileTabularData extends BaseData
{

	@JsonProperty("id_with_different key")
	private String id;

	@JsonProperty("Name with different key")
	private String name;

	@JsonProperty("content with different_KEY")
	private String content;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
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

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}
}
