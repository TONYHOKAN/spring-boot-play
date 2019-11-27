package com.example.springbootplay.utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;


/**
 * Created by Tony Ng on 25/11/2019.
 */
public class TabularFileUtils
{
	private static final char ESCAPE_CHAR = '\\';
	private static final char CSV_FILED_SEPARATOR = CsvSchema.DEFAULT_COLUMN_SEPARATOR;
	private static final char TSV_FILED_SEPARATOR = '\t';
	public static final String CSV_FILE_EXTENSION = ".csv";
	public static final String TSV_FILE_EXTENSION = ".tsv";

	private static final CsvMapper DEFAULT_CSV_MAPPER = new CsvMapper()
	{{
		this.enable(CsvGenerator.Feature.ESCAPE_CONTROL_CHARS_WITH_ESCAPE_CHAR);
		this.enable(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS);
	}};

	private static final Logger LOG = LogManager.getLogger(TabularFileUtils.class);

	public static <T> List<T> readCSV(File file, Class<T> mapClass) throws Exception
	{
		return mapTabularFile(file, mapClass, CSV_FILED_SEPARATOR);
	}

	public static <T> List<T> readTSV(File file, Class<T> mapClass) throws Exception
	{
		return mapTabularFile(file, mapClass, TSV_FILED_SEPARATOR);
	}

	private static <T> List<T> mapTabularFile(File file, Class<T> mapClass, char delimiter) throws Exception
	{
		Objects.requireNonNull(file);
		Objects.requireNonNull(mapClass);

		LOG.debug("Start reading file [{}] using Object Type [{}]", file, mapClass);

		CsvMapper mapper = new CsvMapper();
		mapper.enable(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS);
		mapper.enable(CsvGenerator.Feature.ESCAPE_CONTROL_CHARS_WITH_ESCAPE_CHAR);

		// withSkipFirstDataRow to delete header line
		CsvSchema schema = prepareSchema(DEFAULT_CSV_MAPPER
				.schemaFor(mapClass)
				.withSkipFirstDataRow(true)
				.withColumnSeparator(delimiter));

		MappingIterator<T> it = mapper.readerFor(mapClass)
				.with(schema)
				.readValues(file);

		List<T> mappedObjectList = it.readAll();

		return mappedObjectList;
	}

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
				mapper.enable(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS);
				mapper.enable(CsvGenerator.Feature.ESCAPE_CONTROL_CHARS_WITH_ESCAPE_CHAR);
				CsvSchema schema = prepareSchema(DEFAULT_CSV_MAPPER
						.schemaFor(mappedObjectList.get(0).getClass())
						.withColumnSeparator(delimiter)
						.withHeader());

				mapper
						.writer(schema)
						.writeValues(temp)
						.writeAll(mappedObjectList)
						.close();
			}
			else
			{
				LOG.error("mappedObjectList should not be empty!! for: [{}]", finalPath);
			}
		}
		catch (Exception e)
		{
			LOG.error("Error writing file [{}] with {} records", temp.getPath(), mappedObjectList.size());
			throw e;
		}

		// Remove .tmp extension if no Exception occurred
		Files.move(temp.toPath(), output.toPath(), StandardCopyOption.REPLACE_EXISTING);
		LOG.info("Finish writing file [{}]", output.getPath());
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

	protected static <T> CsvSchema prepareSchema(CsvSchema schema)
	{
		return schema
				.withQuoteChar(CsvSchema.DEFAULT_QUOTE_CHAR)
				.withEscapeChar(ESCAPE_CHAR);
	}
}
