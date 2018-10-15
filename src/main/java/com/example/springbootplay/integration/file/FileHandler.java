package com.example.springbootplay.integration.file;

import java.io.File;


/**
 * Created by Tony Ng on 15/10/2018.
 */
public interface FileHandler
{
	// Throw Exception to let errorHandler handle file transfer
	File process(File file) throws Exception;
}
