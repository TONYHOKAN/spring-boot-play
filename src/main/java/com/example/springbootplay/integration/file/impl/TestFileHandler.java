package com.example.springbootplay.integration.file.impl;

import com.example.springbootplay.integration.file.FileHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

import java.io.File;


/**
 * Created by Tony Ng on 15/10/2018.
 */
@MessageEndpoint
public class TestFileHandler implements FileHandler
{

	private final Logger LOG = LogManager.getLogger(this.getClass());

	@Override
	@ServiceActivator(inputChannel = "testFileHandlerChannel", outputChannel = "doneFileChannel")
	public File process(File file) throws Exception
	{
		LOG.info("Start processing {}",  file.getAbsolutePath());

		try
		{
			// do business logic
		}
		catch (Exception e)
		{
			throw e;
		}

		return file;
	}

	@Bean
	public MessageChannel testFileHandlerChannel() { return new DirectChannel(); }
}
