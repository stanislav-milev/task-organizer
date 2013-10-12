package org.btb.timer.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

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

	static Logger log = Logger.getLogger(TaskOrganizerC.class);

	private TaskOrganizerV view;

	/**
	 * Constructor.
	 */
	public TaskOrganizerC() {
		GUIThread gtView = new GUIThread();
		gtView.start();
		
		setListeners(gtView);
		for (int i = 0; i < 4; i++) {
			addNewTask(true);
		}
		view.showTheUI();
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
		view = gtView.getGui();
		view.getBtnNewTask().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object actionEventSource = e.getSource();
		if (actionEventSource.equals(view.getBtnNewTask())) {
			addNewTask(false);
		} else if (actionEventSource instanceof JButton) {
			if ("Delete".equals(((JButton) actionEventSource).getText())) {
				removeTask((TaskPanelV) ((JButton) actionEventSource).getParent());
			}
		}		
	}

	/**
	 * Removes the given Tasks from the list.
	 * @param task for removal
	 */
	private void removeTask(TaskPanelV task) {
		log.info("Removing task : " + task.getTaskName());
		
		task.getBtnDelete().removeActionListener(this);
		
		view.removeTask(task);
	}

	/**
	 * Adds a new Task panel and sets the listeners to its components.
	 * @param isLoadingPhase true if the adding is executing during the initial loading of tasks
	 */
	private void addNewTask(boolean isLoadingPhase) {
		if (!isLoadingPhase) {
			log.info("Adding a new task.");
		}
		TaskPanelV newTask = new TaskPanelV();
		view.addTask(newTask);
		newTask.getBtnDelete().addActionListener(this);
	}

}
