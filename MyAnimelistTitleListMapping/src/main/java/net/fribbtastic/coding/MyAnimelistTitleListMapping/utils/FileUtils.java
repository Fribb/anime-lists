/**
 * 
 */
package net.fribbtastic.coding.MyAnimelistTitleListMapping.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * @author Fribb
 *
 */
public class FileUtils {
	private static Logger logger = Logger.getLogger(FileUtils.class);

	/**
	 * read the content of a file and return it as a String
	 * 
	 * @param path - the path to the file
	 * @return the content of the file as String or null if the file doesn't exist or there was an error
	 */
	public static String readFile(String path) {
		
		File file = new File(path);
		
		if (file.exists()) {
			logger.debug("File exists, reading content");
			
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				StringBuffer buffer = new StringBuffer();
				String line;
				
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				
				reader.close();
				
				return buffer.toString();
				
			} catch (FileNotFoundException e) {
				logger.error("File was not found: ", e);
				return null;
			} catch (IOException e) {
				logger.error("An error occured while reading the file", e);
				return null;
			}
			
		} else {
			logger.debug("File doesn't exist, returning");
			return null;
		}
	}

	/**
	 * write the content to the file
	 * 
	 * @param content - the content that should be written to the file 
	 * @param path - the path to the file
	 */
	public static void writeFile(String content, String path) {
		
		File file = new File(path);
		
		// check if the file structure already exists, if not then create it
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		
		try {
			FileWriter writer = new FileWriter(file);
			
			writer.write(content);
			
			writer.close();
		} catch (IOException e) {
			logger.error("An error occured while writing the file", e);
		}
	}
}