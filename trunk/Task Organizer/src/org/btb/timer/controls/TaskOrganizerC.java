package org.btb.timer.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;
import org.btb.timer.gui.TaskOrganizerV;
import org.btb.timer.gui.TaskPanelV;
import org.btb.timer.util.GUIThread;

/**
 * Controller of the Task Organizer
 * @author Stanislav Milev
 * @created on Oct 3, 2013
 */
public class TaskOrganizerC implements ActionListener {

	static Logger logger = Logger.getLogger(TaskOrganizerC.class);

	private TaskOrganizerV view;

	/**
	 * Constructor.
	 */
	public TaskOrganizerC() {
		GUIThread gtView = new GUIThread(4);
		gtView.start();
		view = gtView.getGui();
		
		setListeners(gtView);
	}
	
	/**
	 * Initializes listeners.
	 * @param gtView
	 */
	private void setListeners(GUIThread gtView) {
		while(gtView.isNotReady()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		view.getBtnNewTask().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object actionEventSource = e.getSource();
		if (actionEventSource.equals(view.getBtnNewTask())) {
			addNewTask();
		}
	}

	/**
	 * Adds a new Task panel and sets the listeners to its components.
	 */
	private void addNewTask() {
		logger.info("Adding a new task.");
		TaskPanelV newTask = new TaskPanelV();
		view.addTask(newTask);
		
	}

}
