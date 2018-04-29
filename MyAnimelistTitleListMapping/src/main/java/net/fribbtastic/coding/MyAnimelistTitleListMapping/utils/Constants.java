/**
 * 
 */
package net.fribbtastic.coding.MyAnimelistTitleListMapping.utils;

import org.json.JSONArray;

/**
 * @author Fribb
 *
 */
public class Constants {
	
	/**
	 * stores the current titles list
	 */
	public static JSONArray animeTitles;
	
	/**
	 * stores the current mapping list
	 */
	public static JSONArray animeMapping;

	/**
	 * stores the complete title list
	 */
//	public static JSONArray animeTitles_full;
	
	/**
	 * stores the complete mapping list
	 */
//	public static JSONArray animeMapping_full;
	
	/**
	 * folder that stores all anime-mapping files
	 */
	public static String mappingFolder = "anime-mapping";
	
	/**
	 * folder that stores all anime-titles files
	 */
	public static String titlesFolder = "anime-titles";
	
	/**
	 * the current ID
	 */
	public static Integer currentID;
	
	/**
	 * the myanimelist API URL
	 */
	public static String malURL = "http://atarashii.fribbtastic.net/web/2.1/anime/${id}";
	
	/**
	 * the token for TheTVDB
	 */
	public static String TVDBToken;
	
	/**
	 * the path to the mapping
	 */
	public static String animeMappingPath;
	
	/**
	 * the path to the titles
	 */
	public static String animeTitlesPath;

}
