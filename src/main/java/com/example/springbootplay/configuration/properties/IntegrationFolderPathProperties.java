package com.example.springbootplay.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * Created by Tony Ng on 13/10/2018.
 */
@Component
@ConfigurationProperties(prefix = "integration.folder.path")
public class IntegrationFolderPathProperties extends BaseProperties
{
	private String base;
	private String input;
	private String processing;
	private String done;
	private String error;
	private String output;

	public String getBase()
	{
		return base;
	}

	public void setBase(String base)
	{
		this.base = base;
	}

	public String getInput()
	{
		return input;
	}

	public void setInput(String input)
	{
		this.input = input;
	}

	public String getProcessing()
	{
		return processing;
	}

	public void setProcessing(String processing)
	{
		this.processing = processing;
	}

	public String getDone()
	{
		return done;
	}

	public void setDone(String done)
	{
		this.done = done;
	}

	public String getError()
	{
		return error;
	}

	public void setError(String error)
	{
		this.error = error;
	}

	public String getOutput()
	{
		return output;
	}

	public void setOutput(String output)
	{
		this.output = output;
	}
}
