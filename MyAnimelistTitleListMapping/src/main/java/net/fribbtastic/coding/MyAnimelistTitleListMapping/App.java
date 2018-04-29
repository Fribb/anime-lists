package net.fribbtastic.coding.MyAnimelistTitleListMapping;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import net.fribbtastic.coding.MyAnimelistTitleListMapping.utils.Constants;
import net.fribbtastic.coding.MyAnimelistTitleListMapping.utils.JSONUtils;
import net.fribbtastic.coding.MyAnimelistTitleListMapping.utils.MyAnimeListUtils;
import net.fribbtastic.coding.MyAnimelistTitleListMapping.utils.PropertyUtils;
import net.fribbtastic.coding.MyAnimelistTitleListMapping.utils.TheMovieDBUtils;
import net.fribbtastic.coding.MyAnimelistTitleListMapping.utils.TheTVDBUtils;
import net.fribbtastic.coding.MyAnimelistTitleListMapping.utils.Utils;

/**
 * 
 * @author Fribb
 *
 */
public class App {
	private static Logger logger = Logger.getLogger(App.class);
	
	/**
	 * Run the program and get the list of titles from the API for the current iteration as ID,
	 * use this list to get the ID for the mapping between MyAnimeList and TheTVDB.com and TheMovieDB.org 
	 */
	public void run() {
		logger.info("starting fetching titlelist and mapping");
		
		JSONObject actualTitles = null;
		
		if (PropertyUtils.getPropertyValue(PropertyUtils.TITLELIST).equalsIgnoreCase("true")) {
			
			// load existing JSONObject for this ID
			JSONObject existingTitles = JSONUtils.getExistingEntry("id", Constants.currentID, Constants.animeTitles);
			
			// get the response from the MAL API for the current ID
			JSONObject jsonResponse = JSONUtils.getJSONResponse(Constants.currentID);
			
			// extract all available titles (or error message) from the response
			JSONObject titles = MyAnimeListUtils.getTitles(jsonResponse);
			
			// update existing entry
			if (existingTitles != null) {
				JSONUtils.updateEntry(existingTitles, titles);
				actualTitles = existingTitles;
				logger.info("titles added");
			} else {
				Constants.animeTitles.put(titles);
				actualTitles = titles;
				logger.info("titles added");
			}
		}
		
		if (PropertyUtils.getPropertyValue(PropertyUtils.MAPPING).equalsIgnoreCase("true")) {
			
			// load existing JSONObject for this ID
			JSONObject existingMapping = JSONUtils.getExistingEntry("mal_id", Constants.currentID, Constants.animeMapping);
			
			JSONObject mapping = new JSONObject();
			
			// add the MyAnimeList ID
			mapping.put("mal_id", actualTitles.getInt("id"));
			
			// decide which title to use
			String titleToSearch = decideTitle(actualTitles);
			
			// request the id from either TheTVDB or TheMovieDB when the type is either TV or OVA or Movie
			if (actualTitles.has("type")) {
				String type = actualTitles.getString("type");
				
				if (type.equalsIgnoreCase("TV") || type.equalsIgnoreCase("OVA")) {
					// request id from TheTVDB.com
					mapping.put("thetvdb_id", TheTVDBUtils.getID(titleToSearch));
					
				} else if(type.equalsIgnoreCase("Movie")) {
					
					// request Data from TheMovieDB
					mapping.put("themoviedb_id", TheMovieDBUtils.getID(titleToSearch));
				}
			} else {
				Constants.currentID++;
				Utils.writeFiles();
				return;
			}
			
			// update the existing mapping
			if (existingMapping != null) {
				JSONUtils.updateEntry(existingMapping, mapping);
				logger.info("mapping added");
			} else {
				Constants.animeMapping.put(mapping);
				logger.info("mapping added");
			}
			
		}
		Constants.currentID++;
		
		Utils.writeFiles();
	}

	/**
	 * decide which title should be used 
	 * 
	 * The prioritization is 1. English 2. general title
	 * 
	 * @param actualTitles - the JSONObject that contains all possible titles
	 * @return the title that should be used 
	 */
	private String decideTitle(JSONObject actualTitles) {
		if (actualTitles.has("english")) {
			return actualTitles.getJSONArray("english").getString(0);
		} else if (actualTitles.has("synonyms")) {
			return actualTitles.getJSONArray("synonyms").getString(0);
		} else if (actualTitles.has("title")) {
			return actualTitles.getString("title");
		}
		return null;
	}
}