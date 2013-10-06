package org.btb.timer;

import java.io.IOException;

import org.btb.timer.controls.TaskOrganizerC;
import org.btb.timer.controls.TimerC;
import org.btb.timer.data.TaskO;
import org.btb.timer.util.DataStore;
import org.btb.timer.util.IConstants;

/**
 * Starting class for the Task Organizer
 * @author Stanislav Milev
 * @date 02.09.2008
 */
public class Start {

	//TODO cleanup
	
	/**
	 * Main method.
	 * @param args
	 */
	public static void main(String[] args) {
		new TaskOrganizerC();
//		TaskO timerO = null;
//		try {
//			timerO = (TaskO) DataStore.getObject(IConstants.DEFAULT_SAVE_FILE_PATH);
//		} catch (IOException e) {}
//		
//		if (timerO != null) {
//			new TimerC(timerO);
//		} else
//		new TimerC();
	}

}
