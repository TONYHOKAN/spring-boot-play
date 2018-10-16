package com.example.springbootplay.integration.file.impl;

import com.example.springbootplay.data.TestFileData;
import com.example.springbootplay.integration.file.FileHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

import java.io.File;
import java.util.List;


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
		LOG.info("Start processing {}", file.getAbsolutePath());

		try
		{
			// do business logic

			List<TestFileData> testFileDataList = TabularFileReader.mapCSV(file, TestFileData.class);

			testFileDataList.forEach(testFileData -> {
				LOG.info(testFileData);
			});

		}
		catch (Exception e)
		{
			throw e;
		}

		return file;
	}

	/**
	 * define file handler channel inside handler class to make it easy to understand
	 * @return
	 */
	@Bean
	public MessageChannel testFileHandlerChannel()
	{
		return new DirectChannel();
	}
}
