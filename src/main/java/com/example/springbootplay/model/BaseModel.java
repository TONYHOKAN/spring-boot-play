package com.example.springbootplay.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * Created by Tony Ng on 22/10/2018.
 */
public class BaseModel
{
	// controlled by DB scheme
	private Date creationDate;
	private Date lastModifiedDate;

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public Date getCreationDate()
	{
		return creationDate;
	}

	public Date getLastModifiedDate()
	{
		return lastModifiedDate;
	}

}
