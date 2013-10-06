package org.btb.timer.util;

import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Contains the configuration of the app.
 * @author Stanislav Milev
 * @created on Oct 6, 2013
 */
public class Configuration {

	static Logger logger = Logger.getLogger(Configuration.class);

    private static Configuration instance;
    private Map config;

    /**
     * Creates the instance of the configuration class.
     */
	private Configuration(){
		
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
}
