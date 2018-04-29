/**
 * 
 */
package net.fribbtastic.coding.MyAnimelistTitleListMapping;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import net.fribbtastic.coding.MyAnimelistTitleListMapping.utils.Constants;
import net.fribbtastic.coding.MyAnimelistTitleListMapping.utils.PropertyUtils;
import net.fribbtastic.coding.MyAnimelistTitleListMapping.utils.Utils;

/**
 * @author Fribb
 *
 */
public class Main {
	private static Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		// load the properties from file
		PropertyUtils.loadProperties();
		
		// load the properties for use in the filenames and for the thread scheduler
		Integer iterations = Integer.valueOf(PropertyUtils.getPropertyValue(PropertyUtils.ITERATIONS));
		Integer time = Integer.valueOf(PropertyUtils.getPropertyValue(PropertyUtils.TIME));
		Integer start = Integer.valueOf(PropertyUtils.getPropertyValue(PropertyUtils.STARTID));
		Integer end = Integer.valueOf(PropertyUtils.getPropertyValue(PropertyUtils.ITERATIONS)) + start;
		
		Constants.currentID = start;
		
		Utils.buildFileNames(start, end);
		
		Utils.loadFiles();
		
		logger.info("Starting Program: v" + PropertyUtils.getPropertyValue(PropertyUtils.VERSION));
		
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(Main::run, 0, 15, TimeUnit.SECONDS);
		executorService.schedule(new Runnable() {
			
			@Override
			public void run() {
				executorService.shutdown();
				logger.info("end reached");
				Utils.writeFiles();
			}
		}, time * iterations, TimeUnit.SECONDS);
	}
	
	public static void run() {
		App app = new App();
		app.run();
	}
}