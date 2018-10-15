package com.example.springbootplay.exception;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.ErrorHandler;

import java.io.File;


public class FileIntegrationExceptionHandler implements ErrorHandler
{
	private static final Logger LOG = LogManager.getLogger(FileIntegrationExceptionHandler.class);

	private MessageChannel errorChannel;

	public FileIntegrationExceptionHandler(MessageChannel errorChannel)
	{
		this.errorChannel = errorChannel;
	}

	@Override
	public void handleError(Throwable throwable)
	{
		LOG.error("File integration error ", throwable);

		if(throwable.getCause() instanceof MessagingException)
		{
			MessagingException exception = (MessagingException) throwable.getCause();

			Object payload = exception.getFailedMessage().getPayload();
			if(payload instanceof File)
			{
				errorChannel.send(MessageBuilder.withPayload((File) payload).build());
			}
		}
	}
}
