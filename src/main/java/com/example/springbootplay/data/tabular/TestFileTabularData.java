package com.example.springbootplay.data.tabular;

import com.example.springbootplay.data.BaseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Created by Tony Ng on 15/10/2018.
 * attribute sequence does not matter
 * @JsonPropertyOrder to make sure mapping csv column number(header ignored) in read/write to correct attribute sequence, must same with name in @JsonProperty
 * @JsonProperty for writing CSV as header name
 */
@JsonPropertyOrder({"id header", "name header", "content header"})
public class TestFileTabularData extends BaseData
{

	@JsonProperty("id header")
	private String id;

	@JsonProperty("name header")
	private String name;

	@JsonProperty("content header")
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
