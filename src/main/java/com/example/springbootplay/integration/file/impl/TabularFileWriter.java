//package com.example.springbootplay.integration.file.impl;
//
//import com.fasterxml.jackson.databind.MappingIterator;
//import com.fasterxml.jackson.dataformat.csv.CsvMapper;
//import com.fasterxml.jackson.dataformat.csv.CsvSchema;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.util.CollectionUtils;
//
//import java.io.File;
//import java.io.PrintWriter;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
//
///**
// * Created by Tony Ng on 16/10/2018.
// */
//public class TabularFileWriter
//{
//	private static final char CSV_FILED_SEPARATOR = ',';
//	private static final char TSV_FILED_SEPARATOR = '\t';
//	public static final String CSV_FILE_EXTENSION = ".csv";
//	public static final String TSV_FILE_EXTENSION = ".tsv";
//
//	private static final Logger LOG = LogManager.getLogger(TabularFileWriter.class);
//
//	public static void writeCSV() throws Exception
//	{
//
//	}
//
//	public static void writeTSV() throws Exception
//	{
//
//	}
//
//	protected static <T> void writeTabularFile(String finalPath, List<T> mappedObjectList) throws Exception
//	{
//		File temp = new File(finalPath.concat(".tmp"));
//
//		File output = new File(finalPath);
//
//		try (PrintWriter writer = new PrintWriter(temp, StandardCharsets.UTF_8.name()))
//		{
//			if (!CollectionUtils.isEmpty(mappedObjectList))
//			{
//				LOG.info("Start writing file [{}] with {} records", temp.getPath(), content.size());
//
//				// Get the first object for list for header reference
//				LinkedHashMap<String, Property> propertyMap = this.getHeader(content.get(0).getClass());
//
//				// Append Header
//				List<String> header = new ArrayList<>();
//				for(Property property : propertyMap.values())
//				{
//					header.add(property.getHeader());
//				}
//				writer.println(String.join(delimiter, header));
//
//				// Append Body
//				for (Object line : content)
//				{
//					Map<String, String> valueMap = this.getValue(line, propertyMap);
//
//					List<String> details = new ArrayList<>();
//					for (String field : propertyMap.keySet())
//					{
//						if (valueMap.containsKey(field))
//						{
//							details.add(valueMap.get(field));
//						}
//					}
//
//					writer.println(String.join(delimiter, details));
//				}
//			}
//		}
//		catch (Exception e)
//		{
//			throw e;
//		}
//
//		// Remove .tmp extension if no Exception occurred
//		if (temp.renameTo(output))
//		{
//			LOG.info("Finish writing file [{}]", output.getPath());
//		}
//		else
//		{
//			LOG.error("Failed to rename file [{}] to [{}]", temp.getPath(), output.getPath());
//		}
//	}
//
//	protected static String prepareCSV(String path, String fileName)
//	{
//		fileName = fileName.trim();
//
//		if(!fileName.endsWith(CSV_FILE_EXTENSION))
//		{
//			fileName = fileName.concat(CSV_FILE_EXTENSION);
//		}
//
//		return prepareFile(path, fileName);
//	}
//
//	protected static String prepareTSV(String path, String fileName)
//	{
//		fileName = fileName.trim();
//
//		if(!fileName.endsWith(TSV_FILE_EXTENSION))
//		{
//			fileName = fileName.concat(TSV_FILE_EXTENSION);
//		}
//
//		return prepareFile(path, fileName);
//	}
//
//	protected static String prepareFile(String path, String fileName)
//	{
//		path = path.trim();
//
//		fileName = fileName.trim();
//
//		if(!path.endsWith("/"))
//		{
//			path = path.concat("/");
//		}
//
//		if(fileName.startsWith("/"))
//		{
//			fileName = fileName.substring(1, fileName.length());
//		}
//
//		File directory = new File(path);
//
//		if(!directory.exists())
//		{
//			boolean created = directory.mkdirs();
//		}
//
//		return path.concat(fileName);
//	}
//}
