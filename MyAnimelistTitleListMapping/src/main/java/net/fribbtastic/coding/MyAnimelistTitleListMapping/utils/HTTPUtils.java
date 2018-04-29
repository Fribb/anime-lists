/**
 * 
 */
package net.fribbtastic.coding.MyAnimelistTitleListMapping.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * @author Fribb
 *
 */
public class HTTPUtils {
	private static Logger logger = Logger.getLogger(HTTPUtils.class);

	/**
	 * Get the Response of an URL
	 * 
	 * @param urlString - the URL as String
	 * @return the Response as String
	 */
	public static String getResponse(String urlString) {
		
		logger.debug("Sending request to " + urlString);

		try {
			URL url = new URL(urlString);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("GET");
			
			Integer responseCode = connection.getResponseCode();
			
			logger.debug("Response code was " + responseCode);
			
			BufferedReader reader = null;
			
			if (responseCode == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			}
			
			String line;
			StringBuffer stringBuffer = new StringBuffer();
			
			while ((line = reader.readLine()) != null) {
				stringBuffer.append(line);
			}
			
			reader.close();
			connection.disconnect();
			
			return stringBuffer.toString();
			
		} catch (MalformedURLException e) {
			logger.error("The URL is malformed", e);
		} catch (IOException e) {
			logger.error("An error occured while requesting a response from the API", e);
		}
		return null;
	}

	/**
	 * Get the Response of an URL
	 * 
	 * @param urlString - the URL as String
	 * @return the Response as String
	 */
	public static String getResponse(String urlString, String token) {
		
		logger.debug("Sending request to " + urlString);

		try {
			URL url = new URL(urlString);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("GET");
			connection.setRequestProperty("authorization", "Bearer " + token);
			
			Integer responseCode = connection.getResponseCode();
			
			logger.debug("Response code was " + responseCode);
			
			BufferedReader reader = null;
			
			if (responseCode == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			}
			
			String line;
			StringBuffer stringBuffer = new StringBuffer();
			
			while ((line = reader.readLine()) != null) {
				stringBuffer.append(line);
			}
			
			reader.close();
			connection.disconnect();
			
			return stringBuffer.toString();
			
		} catch (MalformedURLException e) {
			logger.error("The URL is malformed", e);
		} catch (IOException e) {
			logger.error("An error occured while requesting a response from the API", e);
		}
		return null;
	}

	/**
	 * Get the Reponse of an URL with posting a message
	 * 
	 * @param url - the URL 
	 * @param message - the message that will be posted
	 * @return 
	 */
	public static String postReponse(String urlString, String message) {
		logger.debug("Post message: " + message);
		logger.debug("Target: " + urlString);
		
		try {
			URL url = new URL(urlString);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("charset", PropertyUtils.getPropertyValue(PropertyUtils.ENCODING));
			connection.setUseCaches(false);
			
			OutputStreamWriter outputWriter = new OutputStreamWriter(connection.getOutputStream());
			outputWriter.write(message);
			outputWriter.flush();
			
			Integer responseCode = connection.getResponseCode();
			
			logger.debug("Response code was " + responseCode);
			
			BufferedReader reader = null;
			
			if (responseCode == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			}
			
			String line;
			StringBuffer stringBuffer = new StringBuffer();
			
			while ((line = reader.readLine()) != null) {
				stringBuffer.append(line);
			}
			
			reader.close();
			connection.disconnect();
			
			return stringBuffer.toString();
			
		} catch (MalformedURLException e) {
			logger.error("The URL is malformed", e);
		} catch (IOException e) {
			logger.error("An error occured", e);
		}
		return null;
	}

}
