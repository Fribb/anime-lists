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
public class TheTVDBUtils {
	private static Logger logger = Logger.getLogger(TheTVDBUtils.class);
	
	/**
	 * the authentication URL to request a token
	 */
	private static String AUTH_URL = "https://api.thetvdb.com/login";
	
	/**
	 * the authentication string to request a token (POST)
	 */
	private static String AUTH_STRING = "{\"apikey\":\"${apy_key}\"}";
	
	/**
	 * the API URL of TheTVDB.com
	 */
	private static String API_URL = "https://api.thetvdb.com/search/series?name=${title}";

	/**
	 * @param title - string that contains the title
	 * @return the id found on TheTVDB or null
	 */
	public static Integer getID(String title) {
		logger.debug("Searching on TheTVDB");
		
		try {
			Map<String, String> data = new HashMap<String, String>();
			data.put("title", URLEncoder.encode(title, PropertyUtils.getPropertyValue(PropertyUtils.ENCODING)));
			String url = StrSubstitutor.replace(API_URL, data);
			
			requestToken();
			
			String response = HTTPUtils.getResponse(url, Constants.TVDBToken);
			
			if (response != null) {
				JSONObject responseObj = new JSONObject(response);
				
				if (responseObj.has("data")) {
					JSONArray tvdbdata = new JSONObject(response).getJSONArray("data");
					
					JSONObject entry = tvdbdata.getJSONObject(0);
					
					if (entry.has("id")) {
						return entry.getInt("id");
					}
				}
			} 
		} catch (UnsupportedEncodingException e) {
			logger.error("Encoding is not supported", e);
		}
		
		return -1;
	}

	/**
	 * Request the Token from the login URL
	 * 
	 */
	private static void requestToken() {
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("apy_key", PropertyUtils.getPropertyValue(PropertyUtils.THETVDBKEY));
		
		String message = StrSubstitutor.replace(AUTH_STRING, data);
		
		if (Constants.TVDBToken == null) {
			String tokenStr = HTTPUtils.postReponse(AUTH_URL, message);
			
			JSONObject token = new JSONObject(tokenStr);
			
			if (token.has("token")) {
				Constants.TVDBToken = token.getString("token");
			} else if (token.has("Error")) {
				logger.error("Token request returned error: " + token.getString("Error"));
			}
		}
	}
}