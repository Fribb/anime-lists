/**
 * 
 */
package net.fribbtastic.coding.MyAnimelistTitleListMapping.utils;

import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 * @author Fribb
 *
 */
public class MyAnimeListUtils {
	private static Logger logger = Logger.getLogger(MyAnimeListUtils.class);

	/**
	 * extract the available titles from the response 
	 * 
	 * @param jsonResponse - the response in JSON format
	 * @param currentIteration - the current iteration as ID
	 * @return a JSONObject filled with the ID and Titles only
	 */
	public static JSONObject getTitles(JSONObject jsonResponse) {
		logger.debug("extracting titles");
		
		JSONObject result = new JSONObject();
		
		// add the ID to the result
		if (jsonResponse.has("id")) {
			result.put("id", jsonResponse.getInt("id"));
		}
		
		if (jsonResponse.has("type")) {
			result.put("type", jsonResponse.getString("type"));
		}
		
		// add the default title to the result
		if (jsonResponse.has("title")) {
			result.put("title", jsonResponse.getString("title"));
		}
		
		// add the other titles to the result
		if (jsonResponse.has("other_titles")) {
			JSONObject other_titles = jsonResponse.getJSONObject("other_titles");
			
			// add the english titles to the result
			if (other_titles.has("english")) {
				result.put("english", other_titles.getJSONArray("english"));
			}
			
			// add the japanese titles to the result
			if (other_titles.has("japanese")) {
				result.put("japanese", other_titles.getJSONArray("japanese"));
			}
			
			// add the synonyms to the result
			if (other_titles.has("synonyms")) {
				result.put("synonyms", other_titles.getJSONArray("synonyms"));
			}
		}
		
		// when there is an error message, add it to the result with the current iteration as ID
		if (jsonResponse.has("error")) {
			result.put("id", Constants.currentID);
			result.put("error", jsonResponse.get("error"));
		}
		
		return result;
	}
}