package org.btb.timer.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Contains the configuration of the app.
 * @author Stanislav Milev
 * @created on Oct 6, 2013
 */
public class Configuration {

	static Logger log = Logger.getLogger(Configuration.class);

    private static Configuration instance;
    
	private Properties config;

    /**
     * Creates the instance of the configuration class.
     */
	private Configuration(){
		config = new Properties();
		try {
			config.load(new FileInputStream(IConstants.CONFIGURATION));
	    } catch (FileNotFoundException e) {
	    	log.error(e);
	    } catch (IOException e) {
	    	log.fatal(e);
	    }
	}

    /**
     * @return the instance of the configuration.
     */
    public static Configuration getInstance(){
        if(instance == null){
        	instance = new Configuration();
        }
        return instance;
    }
    
    /**
     * @return the number of max displayed tasks at once
     */
    public int getMaxDisplayedTasks() {
    	if (config.containsKey(IConstants.MAX_DISPLAYED_TASKS)) {
    		String value = config.get(IConstants.MAX_DISPLAYED_TASKS).toString();
    		try {
    			return Integer.valueOf(value);
    		} catch (NumberFormatException e) {
    			log.error(value + " is not a valid value for " +
    					IConstants.MAX_DISPLAYED_TASKS + " a value of " +
    					IConstants.DEFAULT_MAX_DISPLAYED_TASKS + 
    					" will be used instead.");
    		}
    	}
    	config.put(IConstants.MAX_DISPLAYED_TASKS, IConstants.DEFAULT_MAX_DISPLAYED_TASKS);
    	return IConstants.DEFAULT_MAX_DISPLAYED_TASKS;
    }
    
}
