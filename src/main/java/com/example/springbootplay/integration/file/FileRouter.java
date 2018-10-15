package com.example.springbootplay.integration.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

import java.io.File;


/**
 * Created by Tony Ng on 15/10/2018.
 */
@MessageEndpoint
public class FileRouter
{
	private final static Logger LOG = LogManager.getLogger(FileRouter.class);

	@Router(inputChannel = "processFileChannel")
	public String route(File file)
	{
		LOG.info("Discover file {}", file);

		if(file.getName().contains("testFile"))
		{
			return "testFileHandlerChannel";
		}
		else
		{
			throw new IllegalArgumentException(String.format("Unknown Incoming File [%s]", file.getName()));
		}
	}
}
