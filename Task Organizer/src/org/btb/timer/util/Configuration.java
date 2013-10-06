package org.btb.timer.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
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
    private Map config;

    /**
     * Creates the instance of the configuration class.
     */
	private Configuration(){
		Properties prop = new Properties();
		try {
		    prop.load(new FileInputStream(IConstants.CONFIGURATION));
	    } catch (FileNotFoundException e) {
	    	log.error(e);
	    } catch (IOException e) {
	    	log.fatal(e);
	    }
	    config = prop;
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
