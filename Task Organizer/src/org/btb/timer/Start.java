package org.btb.timer;

import java.io.IOException;

import org.btb.timer.controls.TimerC;
import org.btb.timer.core.TaskO;
import org.btb.timer.core.TasksO;
import org.btb.timer.util.DataStore;
import org.btb.timer.util.IConstants;

/**
 * Starting class for Timer_D
 * @author Stanislav Milev
 * @date 02.09.2008
 */
public class Start {

	/**
	 * Main method.
	 * @param args
	 */
	public static void main(String[] args) {
		TasksO tasksO = null;
		try {
			tasksO = (TasksO) DataStore.getObject(IConstants.DEFAULT_FILE_PATH);
		} catch (IOException e) {}
		
		if (tasksO == null) {
			tasksO = new TasksO();
		}
		new TimerC(new TasksO());
	}

}
