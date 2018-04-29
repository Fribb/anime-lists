/**
 * 
 */
package net.fribbtastic.coding.MyAnimelistTitleListMapping.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author Fribb
 *
 */
public class PropertyUtils {

	private static Logger logger = Logger.getLogger(PropertyUtils.class);

	// the App wide Properties
	private static Properties PROPERTIES = new Properties();

	// location of the properties file
	private static String FILE = "config.properties";

	// property keys
	public static String VERSION = "project.version";
	public static String STARTID = "project.start.id";
	public static String ENCODING = "project.build.sourceEncoding";
	public static String MAPPING = "project.mapping";
	public static String TITLELIST = "project.titlelist";
	public static String ITERATIONS = "project.iterations";
	public static String TIME = "project.iterations.time";
	public static String PATH = "project.path";
	public static String THETVDBKEY = "project.apikey.thetvdb";
	public static String THEMOVIEDBKEY = "project.apikey.themoviedb";

	/**
	 * Get the value of a given key
	 * 
	 * @param key
	 *            - the key used in the properties
	 * @return the value of the property
	 */
	public static String getPropertyValue(String key) {
		return PROPERTIES.getProperty(key);
	}

	/**
	 * load the properties in the PROPERTIES variable to make them globally
	 * available
	 */
	public static void loadProperties() {
		File propertiesFile = new File(FILE);

		InputStream inputStream = null;

		try {
			inputStream = new FileInputStream(propertiesFile);

			PROPERTIES.load(inputStream);
		} catch (FileNotFoundException e) {
			logger.error("File was not found: ", e);
		} catch (IOException e) {
			logger.error("An error occurred loading the properties file", e);
		}
	}
}