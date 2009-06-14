package org.btb.timer.gui;

/**
 * Interface for all Frames.
 * @author Stanislav Milev
 * @date 21.06.2008
 */
public interface IFrame {

	/**
	 * Initializes the GUI.
	 */
	public void initGUI();
	
	/**
	 * Sets the state of editable and clickable components.
	 * @param state
	 */
	public void setComponentsState(boolean state);
	
}
