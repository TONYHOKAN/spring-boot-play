package com.example.springbootplay.integration.file.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.Objects;


/**
 * Created by Tony Ng on 16/10/2018.
 */
public class TabularFileReader
{
	private static final char CSV_FILED_SEPARATOR = ',';
	private static final char TSV_FILED_SEPARATOR = '\t';

	private static final Logger LOG = LogManager.getLogger(TabularFileReader.class);

	public static <T> List<T> readCSV(File file, Class<T> mapClass) throws Exception
	{
		return mapTabularFile(file, mapClass, CSV_FILED_SEPARATOR);
	}

	public static <T> List<T> mapTSV(File file, Class<T> mapClass) throws Exception
	{
		return mapTabularFile(file, mapClass, TSV_FILED_SEPARATOR);
	}

	private static <T> List<T> mapTabularFile(File file, Class<T> mapClass, char delimiter) throws Exception
	{
		Objects.requireNonNull(file);
		Objects.requireNonNull(mapClass);

		LOG.debug("Start reading file [{}] using Object Type [{}]", file, mapClass);

		CsvMapper mapper = new CsvMapper();
		// withSkipFirstDataRow to delete header line
		CsvSchema schema = mapper.schemaFor(mapClass).withSkipFirstDataRow(true);
		if (TSV_FILED_SEPARATOR == delimiter)
		{
			schema.withColumnSeparator(CSV_FILED_SEPARATOR);
		}
		else
		{
			schema.withColumnSeparator(CsvSchema.DEFAULT_COLUMN_SEPARATOR);
		}

		MappingIterator<T> it = mapper.readerFor(mapClass)
				.with(schema)
				.readValues(file);

		List<T> mappedObjectList = it.readAll();

		return mappedObjectList;
	}
}
