/**
 * 
 */
package net.fribbtastic.coding.MyAnimelistTitleListMapping.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StrSubstitutor;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Fribb
 *
 */
public class TheMovieDBUtils {
	private static Logger logger = Logger.getLogger(TheMovieDBUtils.class);
	
	/**
	 * the API URL for TheMovieDB.org
	 */
	private static String API_URL = "http://api.tmdb.org/3/search/movie?api_key=${api_key}&query=${title}&year=&language=en&include_adult=true";

	/**
	 * @param actualTitles
	 * @return
	 */
	public static Integer getID(String title) {
		logger.debug("Searching on TheMovieDB");
		
		try {
			String encoding = PropertyUtils.getPropertyValue(PropertyUtils.ENCODING);
			String apiKey = PropertyUtils.getPropertyValue(PropertyUtils.THEMOVIEDBKEY);
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("title", URLEncoder.encode(title, encoding));
			data.put("api_key", URLEncoder.encode(apiKey, encoding));
			String url = StrSubstitutor.replace(API_URL, data);
			
			String response = HTTPUtils.getResponse(url);
			
			JSONObject search = new JSONObject(response);
			if (search.has("results")) {
				JSONArray results = search.getJSONArray("results");
				
				if (results.length() > 0) {
					JSONObject entry = results.getJSONObject(0);
					
					return entry.getInt("id");
				} else {
					logger.debug("no results found");
				}
			} else {
				logger.debug("no results found");
			}
			
		} catch (UnsupportedEncodingException e) {
			logger.error("Encoding is not supported", e);
		}
		
		return -1;
	}
}