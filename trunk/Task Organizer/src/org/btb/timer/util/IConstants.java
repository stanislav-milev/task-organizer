package org.btb.timer.util;

/**
 * Interface that contains constants for the timer application.
 * @author Stanislav Milev
 * @date 16.10.2008
 */
public interface IConstants {

	int MINUTE = 60000;
	int SECONDS = 1000;
	int MAX_MINUTES = 60;
	int MAX_HOURS = 24;
	int MAX_SECONDS = 60;
	
	String CONFIGURATION = "configuration.properties";
	
	/*
	 * Default parameter values
	 */
	int DEFAULT_AUTO_SAVE_INTERVAL = 5; //in minutes
	//Default storage file path.
	String DEFAULT_SAVE_FILE_PATH = "saved data.dat";
	int DEFAULT_MAX_DISPLAYED_TASKS = 5;
	int DEFAULT_NUMBER_OF_TASKS = 3;
	boolean DEFAULT_BUTTONS_WITH_ICONS = true;
	
	/*
	 * Parameters
	 */
	String MAX_DISPLAYED_TASKS = "gui.maxDisplayedTasks";
	String BUTTONS_WITH_ICONS = "gui.buttonsWithIcons";
	
}
