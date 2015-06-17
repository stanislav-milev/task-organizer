package org.btb.timer.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.Timer;

import org.apache.log4j.Logger;
import org.btb.timer.data.TaskO;
import org.btb.timer.gui.TaskOrganizerV;
import org.btb.timer.gui.TaskPanelV;
import org.btb.timer.util.DataStore;
import org.btb.timer.util.GUIThread;
import org.btb.timer.util.IConstants;

/**
 * Controller of the Task Organizer
 *
 * @author Stanislav Milev
 * @created on Oct 3, 2013
 */
public class TaskOrganizerC implements ActionListener, WindowListener {

	static Logger log = Logger.getLogger(TaskOrganizerC.class);

	private TaskOrganizerV view;
	private ArrayList<TaskPanelV> tasks;
	private TaskPanelV currentTask;
	private Timer timer;
	private HashMap<String, TaskO> history;

	/**
	 * Constructor.
	 *
	 * @param tasksData
	 *            a list of TaskO
	 * @param history
	 */
	public TaskOrganizerC(List<TaskO> tasksData, HashMap<String, TaskO> history) {
	GUIThread gtView = new GUIThread();
	gtView.start();
	tasks = new ArrayList<TaskPanelV>();
	timer = new Timer(IConstants.SECONDS, this);
	this.history = history;

	if (history == null) {
		this.history = new HashMap<String, TaskO>();
	}

	setListeners(gtView);
	int numberOfTasks = IConstants.DEFAULT_NUMBER_OF_TASKS;
	if (tasksData != null) {
		numberOfTasks = tasksData.size();
	}
	for (int i = 0; i < numberOfTasks; i++) {
		TaskPanelV task = addNewTask(true);
		if (tasksData != null) {
		task.initFields(tasksData.get(i));
		}
	}
	view.showTheUI();
	}

	/**
	 * Initializes listeners.
	 *
	 * @param gtView
	 */
	private void setListeners(GUIThread gtView) {
	while (gtView.isNotReady()) {
		try {
		Thread.sleep(500);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
	}
	view = gtView.getGui();
	view.getBtnNewTask().addActionListener(this);
	view.addWindowListener(this);
	}

	public void actionPerformed(ActionEvent e) {
	Object actionEventSource = e.getSource();
	if (actionEventSource.equals(view.getBtnNewTask())) {
		addNewTask(false);
	} else if (actionEventSource instanceof JButton) {
		if ("Delete".equals(((JButton) actionEventSource).getName())) {
		removeTask((TaskPanelV) ((JButton) actionEventSource).getParent());
		} else if ("Start".equals(((JButton) actionEventSource).getName())) {
		startTask((TaskPanelV) ((JButton) actionEventSource).getParent());
		} else if ("Stop".equals(((JButton) actionEventSource).getName())) {
		stopTask((TaskPanelV) ((JButton) actionEventSource).getParent());
		} else if ("Reset".equals(((JButton) actionEventSource).getName())) {
		resetTask((TaskPanelV) ((JButton) actionEventSource).getParent());
		} else if ("Up".equals(((JButton) actionEventSource).getName())) {
		moveTaskUp((TaskPanelV) ((JButton) actionEventSource).getParent());
		} else if ("Down".equals(((JButton) actionEventSource).getName())) {
		moveTaskDown((TaskPanelV) ((JButton) actionEventSource).getParent());
		}
	}

	if (actionEventSource.equals(timer)) {
		adjustTime();
	}
	}

	/**
	 * Moving the task one position up.
	 *
	 * @param task
	 *            for moving up
	 */
	private void moveTaskUp(TaskPanelV task) {
	log.info("Moving task (" + task.getTaskName() + ") up.");
	int index = tasks.indexOf(task);
	if (index == 0) {
		log.info("The current task is first in the list.");
		return;
	}
	tasks.remove(task);
	index--;
	tasks.add(index, task);

	view.moveTask(index, task);
	}

	/**
	 * Moving the task one position down.
	 *
	 * @param task
	 *            for moving down
	 */
	private void moveTaskDown(TaskPanelV task) {
	log.info("Moving task (" + task.getTaskName() + ") down.");
	int index = tasks.indexOf(task);
	if (index == (tasks.size() - 1)) {
		log.info("The current task is last in the list.");
		return;
	}
	tasks.remove(task);
	index++;
	tasks.add(index, task);

	view.moveTask(index, task);
	}

	/**
	 * Calculates the current state of timer.
	 */
	private void adjustTime() {
	int minutes = currentTask.getMinutes();
	int hours = currentTask.getHours();
	int days = currentTask.getDays();
	int seconds = currentTask.getSeconds();

	seconds++;
	if (seconds == IConstants.MAX_SECONDS) {
		seconds = 0;
		minutes++;
		if (((minutes % IConstants.DEFAULT_AUTO_SAVE_INTERVAL) == 0) || (minutes == 0)) {
		saveState();
		}
		if (minutes == IConstants.MAX_MINUTES) {
		minutes = 0;
		hours++;
		if (hours == IConstants.MAX_HOURS) {
			hours = 0;
			days++;
		}
		}
	}

	currentTask.setTime(minutes, hours, days, seconds);
	}

	/**
	 * Starts the timer of the given task.
	 *
	 * @param task
	 *            for starting
	 */
	private void startTask(TaskPanelV task) {
	log.info("Starting task : " + task.getTaskName());
	// stop the current task
	if (currentTask != null) {
		stopTask(currentTask);
	}
	currentTask = task;
	// start the new task
	timer.start();
	// change the state of the buttons and fields
	task.setComponentsState(false);
	// save state
	saveState();
	}

	/**
	 * Saves the current state of the tasks.
	 */
	private void saveState() {
	List<TaskO> tasksData = new ArrayList<TaskO>();
	for (int i = 0; i < tasks.size(); i++) {
		tasksData.add(tasks.get(i).getTimerO());
	}
	DataStore.saveObject(IConstants.DEFAULT_SAVE_FILE_PATH, tasksData);
	DataStore.saveObject(IConstants.DEFAULT_HISTORY_FILE_PATH, history);
	}

	/**
	 * Stops the timer of the given task.
	 *
	 * @param task
	 *            for stopping
	 */
	private void stopTask(TaskPanelV task) {
	log.info("Stopping task : " + task.getTaskName());
	currentTask = null;
	// stop task
	timer.stop();
	// change the state of the buttons and fields
	task.setComponentsState(true);
	// save state
	saveState();
	}

	/**
	 * Resets the time of the task.
	 *
	 * @param task
	 *            for reseting it's time
	 */
	private void resetTask(TaskPanelV task) {
	log.info("Starting task : " + task.getTaskName());
	task.setTime(0, 0, 0, 0);
	}

	/**
	 * Removes the given Tasks from the list.
	 *
	 * @param task
	 *            for removal
	 */
	private void removeTask(TaskPanelV task) {
	log.info("Removing task : " + task.getTaskName());

	task.getBtnDelete().removeActionListener(this);
	task.getBtnStart().removeActionListener(this);
	task.getBtnStop().removeActionListener(this);
	task.getBtnReset().removeActionListener(this);
	task.getBtnUp().removeActionListener(this);
	task.getBtnDown().removeActionListener(this);
	history.put(task.getTaskName(), task.getTimerO());
	tasks.remove(task);
	view.removeTask(task);
	}

	/**
	 * Adds a new Task panel and sets the listeners to its components.
	 *
	 * @param isLoadingPhase
	 *            true if the adding is executing during the initial loading of tasks
	 * @return the newly created task
	 */
	private TaskPanelV addNewTask(boolean isLoadingPhase) {
	if (!isLoadingPhase) {
		log.info("Adding a new task.");
	}
	TaskPanelV newTask = new TaskPanelV();
	tasks.add(newTask);
	view.addTask(newTask);
	newTask.getBtnDelete().addActionListener(this);
	newTask.getBtnStart().addActionListener(this);
	newTask.getBtnStop().addActionListener(this);
	newTask.getBtnReset().addActionListener(this);
	newTask.getBtnUp().addActionListener(this);
	newTask.getBtnDown().addActionListener(this);
	// setState
	newTask.setComponentsState(true);
	return newTask;
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	public void windowClosing(WindowEvent e) {
	// stop task
	timer.stop();
	// save state
	saveState();
	}

	public void windowOpened(WindowEvent e) {
	// Not used method
	}

	public void windowClosed(WindowEvent e) {
	// Not used method
	}

	public void windowIconified(WindowEvent e) {
	// Not used method
	}

	public void windowDeiconified(WindowEvent e) {
	// Not used method
	}

	public void windowActivated(WindowEvent e) {
	// Not used method
	}

	public void windowDeactivated(WindowEvent e) {
	// Not used method
	}

}
