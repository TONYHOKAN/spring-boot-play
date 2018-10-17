package com.example.springbootplay.integration.file.impl;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.List;


/**
 * Created by Tony Ng on 16/10/2018.
 */
public class TabularFileWriter
{
	private static final char CSV_FILED_SEPARATOR = ',';
	private static final char TSV_FILED_SEPARATOR = '\t';
	public static final String CSV_FILE_EXTENSION = ".csv";
	public static final String TSV_FILE_EXTENSION = ".tsv";

	private static final Logger LOG = LogManager.getLogger(TabularFileWriter.class);

	public static <T> void writeCSV(String path, String fileName, List<T> mappedObjectList) throws Exception
	{
		writeTabularFile(prepareCSV(path, fileName), mappedObjectList, CSV_FILED_SEPARATOR);
	}

	public static <T> void writeTSV(String path, String fileName, List<T> mappedObjectList) throws Exception
	{
		writeTabularFile(prepareTSV(path, fileName), mappedObjectList, TSV_FILED_SEPARATOR);
	}

	private static <T> void writeTabularFile(String finalPath, List<T> mappedObjectList, char delimiter) throws Exception
	{
		File temp = new File(finalPath.concat(".tmp"));

		File output = new File(finalPath);

		try
		{
			if (!CollectionUtils.isEmpty(mappedObjectList))
			{
				LOG.info("Start writing file [{}] with {} records", temp.getPath(), mappedObjectList.size());

				CsvMapper mapper = new CsvMapper();
				CsvSchema schema = mapper.schemaFor(mappedObjectList.get(0).getClass()).withHeader().withColumnSeparator(delimiter);
				mapper.writer(schema).writeValues(temp).writeAll(mappedObjectList).close();
			}
		}
		catch (Exception e)
		{
			LOG.error("Error writing file [{}] with {} records", temp.getPath(), mappedObjectList.size());
			throw e;
		}

		// Remove .tmp extension if no Exception occurred
		if (temp.renameTo(output))
		{
			LOG.info("Finish writing file [{}]", output.getPath());
		}
		else
		{
			LOG.error("Failed to rename file [{}] to [{}]", temp.getPath(), output.getPath());
		}
	}

	protected static String prepareCSV(String path, String fileName)
	{
		fileName = fileName.trim();

		if (!fileName.endsWith(CSV_FILE_EXTENSION))
		{
			fileName = fileName.concat(CSV_FILE_EXTENSION);
		}

		return prepareFile(path, fileName);
	}

	protected static String prepareTSV(String path, String fileName)
	{
		fileName = fileName.trim();

		if (!fileName.endsWith(TSV_FILE_EXTENSION))
		{
			fileName = fileName.concat(TSV_FILE_EXTENSION);
		}

		return prepareFile(path, fileName);
	}

	protected static String prepareFile(String path, String fileName)
	{
		path = path.trim();

		fileName = fileName.trim();

		if (!path.endsWith("/"))
		{
			path = path.concat("/");
		}

		if (fileName.startsWith("/"))
		{
			fileName = fileName.substring(1, fileName.length());
		}

		File directory = new File(path);

		if (!directory.exists())
		{
			boolean created = directory.mkdirs();
		}

		return path.concat(fileName);
	}
}
