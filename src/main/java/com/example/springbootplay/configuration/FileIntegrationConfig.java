package com.example.springbootplay.configuration;

import com.example.springbootplay.configuration.properties.IntegrationFolderPathProperties;
import com.example.springbootplay.exception.FileIntegrationExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.integration.util.DynamicPeriodicTrigger;
import org.springframework.messaging.MessageChannel;
import org.springframework.util.ErrorHandler;

import java.io.File;
import java.util.concurrent.TimeUnit;


/**
 * Created by Tony Ng on 13/10/2018.
 */
@Configuration
@EnableIntegration
public class FileIntegrationConfig
{

	@Autowired
	IntegrationFolderPathProperties integrationFolderPathProperties;

	@Bean
	public MessageChannel inFileChannel() { return new DirectChannel(); }

	@Bean
	public MessageChannel processFileChannel() { return new DirectChannel(); }

	@Bean
	public MessageChannel doneFileChannel() { return new DirectChannel(); }

	@Bean
	public MessageChannel errorFileChannel() { return new DirectChannel(); }

	@Bean
	public ErrorHandler errorHandler()
	{
		return new FileIntegrationExceptionHandler(errorFileChannel());
	}

	@Bean(name = "filePoller")
	public PollerMetadata filePoller()
	{
		PollerMetadata pollerMetadata = new PollerMetadata();
		pollerMetadata.setTrigger(new DynamicPeriodicTrigger(1, TimeUnit.SECONDS));
		pollerMetadata.setMaxMessagesPerPoll(5);
		pollerMetadata.setErrorHandler(errorHandler());
		return pollerMetadata;
	}

	@Bean
	@InboundChannelAdapter(channel = "inFileChannel", poller = @Poller(value = "filePoller"))
	public MessageSource<File> inFileMessageSource()
	{
		//		to avoid duplicate updating via file, we can make use of below filter
		//		CompositeFileListFilter<File> filters = new CompositeFileListFilter<>();
		//		filters.addFilter(new SimplePatternFileListFilter("*.txt"));
		//		filters.addFilter(new LastModifiedFileListFilter());
		//		filters.addFilter((new AcceptOnceFileListFilter<>());
		FileReadingMessageSource source = new FileReadingMessageSource();
		source.setAutoCreateDirectory(true);
		source.setDirectory(new File(integrationFolderPathProperties.getInput()));

		//		source.setFilter(filters);

		return source;
	}

	@Bean
	@ServiceActivator(inputChannel = "inFileChannel")
	public FileWritingMessageHandler messageProcessHandler()
	{
		FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(integrationFolderPathProperties.getProcessing()));
		handler.setFileExistsMode(FileExistsMode.REPLACE);
		handler.setDeleteSourceFiles(true);
		handler.setOutputChannel(processFileChannel());
		return handler;
	}

	@Bean
	@ServiceActivator(inputChannel = "doneFileChannel")
	public FileWritingMessageHandler messageDoneHandler()
	{
		FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(integrationFolderPathProperties.getDone()));
		handler.setFileExistsMode(FileExistsMode.REPLACE);
		handler.setDeleteSourceFiles(true);
		handler.setExpectReply(false);
		return handler;
	}

	@Bean
	@ServiceActivator(inputChannel = "errorFileChannel")
	public FileWritingMessageHandler messageErrorHandler()
	{
		FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(integrationFolderPathProperties.getError()));
		handler.setFileExistsMode(FileExistsMode.REPLACE);
		handler.setDeleteSourceFiles(true);
		handler.setExpectReply(false);
		return handler;
	}
}
