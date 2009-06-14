package org.btb.timer.util;

import org.btb.timer.gui.IFrame;


/**
 * Thread that manages the GUI initialization.
 * @author Stanislav Milev
 * @date 21.06.2008
 */
public class GUIThread extends Thread {

	private IFrame frame;
	private boolean isNotReady = true;
	
	/**
	 * Constructor.
	 * @param frame
	 */
	public GUIThread(IFrame frame) {
		this.frame = frame;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		frame.initGUI();
		isNotReady = false;
	}

	/**
	 * @return true if the GUI is not initialized yet.
	 */
	public boolean isNotReady() {
		return isNotReady;
	}
	
}
