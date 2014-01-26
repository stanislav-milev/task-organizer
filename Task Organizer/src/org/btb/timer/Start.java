package org.btb.timer;

import java.io.IOException;
import java.util.List;

import org.btb.timer.controls.TaskOrganizerC;
import org.btb.timer.data.TaskO;
import org.btb.timer.util.DataStore;
import org.btb.timer.util.IConstants;

/**
 * Starting class for the Task Organizer
 * @author Stanislav Milev
 * @date 02.09.2008
 */
public class Start {

	/**
	 * Main method.
	 * @param args
	 */
	public static void main(String[] args) {
		List<TaskO> tasks = null;
		try {
			tasks = (List<TaskO>) DataStore.getObject(IConstants.DEFAULT_SAVE_FILE_PATH);
		} catch (IOException e) {}

		new TaskOrganizerC(tasks);
	}

}
