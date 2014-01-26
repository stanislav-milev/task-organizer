package org.btb.timer.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.btb.timer.data.TaskO;

/**
 * Utility class for storing and loading data.
 * @author Stanislav Milev
 * @date 16.10.2008
 */
public class DataStore {

	static Logger log = Logger.getLogger(DataStore.class);

	/**
	 * Saves an object into a file.
	 * 
	 * @param path	the path of the file
	 * @param tasks the object that will be stored in the file
	 */
	public static void saveObject(String path, List<TaskO> tasks) {
		ObjectOutput out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(new File(path)));
			out.writeObject(tasks);
			log.info("Saving");
		} catch (IOException e) {
			log.fatal("Can't save to file.", e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				log.fatal("Can't save to file.", e);
			}
		}
	}
	
	/**
	 * Gets an object from a file.
	 * 
	 * @param path the path of the file
	 * @return object the object from the file
	 * @throws IOException thrown when the specified file is not found
	 */
	public static Object getObject(String path) throws IOException {
		ObjectInputStream in 	= null;
		Object result 			= null;
		
		try {
			in = new ObjectInputStream(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			throw new IOException(e.getMessage());
		} catch (IOException e) {
			log.fatal("Can't read from file.", e);
		} 
		try {
			result = in.readObject();
		} catch (ClassNotFoundException e) {
			log.fatal("Can't read from file.", e);
		} catch (ClassCastException e) {
			log.error("There is no correct data in the file.");
			return null;
		} finally {
			in.close();
		}
		
		return result;
	}
	
}
