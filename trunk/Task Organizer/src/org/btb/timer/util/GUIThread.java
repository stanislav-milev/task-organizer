package org.btb.timer.util;

import org.btb.timer.gui.TaskOrganizerV;


/**
 * Thread that manages the GUI initialization.
 * @author Stanislav Milev
 * @date 21.06.2008
 */
public class GUIThread extends Thread {

	private TaskOrganizerV gui;
	private boolean isNotReady = true;
	
	public GUIThread(int initialNumberOfTasks) {
		gui = new TaskOrganizerV(initialNumberOfTasks);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		gui.initGui();
		isNotReady = false;
	}

	/**
	 * @return true if the GUI is not initialized yet.
	 */
	public boolean isNotReady() {
		return isNotReady;
	}

	/**
	 * @return the gui
	 */
	public TaskOrganizerV getGui() {
		return gui;
	}

}
