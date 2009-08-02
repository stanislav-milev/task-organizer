package org.btb.timer.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Timer;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import org.btb.timer.core.TaskO;
import org.btb.timer.core.TasksO;
import org.btb.timer.gui.TimerV;
import org.btb.timer.util.DataStore;
import org.btb.timer.util.GUIThread;
import org.btb.timer.util.IConstants;

/**
 * Main class for the "Timer D" application. Controller.
 * @author Stanislav Milev
 * @date 13.09.2008
 */
public class TimerC implements ActionListener, WindowListener, TreeSelectionListener {

	private TimerV 	view;
	private Timer 	timer;
	private TaskO	currentTask;
	private TasksO	taskList;
	
	/**
	 * Constructor.
	 * @param timerO
	 */
	public TimerC(TasksO tasksO) {
		view = new TimerV();
		GUIThread gtView = new GUIThread(view);
		timer = new Timer(IConstants.MINUTE, this);
		gtView.start();
		
		setListeners(gtView);
		view.setComponentsState(true);
		taskList = tasksO;
		currentTask = tasksO.getCurrentTask();
		view.initOrganizer(tasksO);
	}

	/**
	 * Initializes listeners.
	 * @param gtView
	 */
	private void setListeners(GUIThread gtView) {
		while(gtView.isNotReady()) {}
		view.getBtnAddTask().addActionListener(this);
		view.getBtnResetTimer().addActionListener(this);
		view.getBtnStartTimer().addActionListener(this);
		view.getBtnStopTimer().addActionListener(this);
		view.getFrame().addWindowListener(this);
		view.getTreTaskList().addTreeSelectionListener(this);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object actionEventSource = e.getSource();
		if (actionEventSource.equals(view.getBtnAddTask())) {
			TreePath path = view.addNewTaskNode();
			taskList.setTasksTree(view.getRoot());
			taskList.setCurrentTaskPath(path);
			showSelectedNode();
		} else
		
		if (actionEventSource.equals(view.getBtnResetTimer())) {
			view.setTime(0, 0, 0);
		} else
			
		if (actionEventSource.equals(view.getBtnStartTimer())) {
			view.setComponentsState(false);
			timer.start();
		} else
			
		if (actionEventSource.equals(view.getBtnStopTimer())) {
			timer.stop();
			view.setComponentsState(true);
		} else
			
		if (actionEventSource.equals(timer)) {
			adjustTime();
		}
	}

	/**
	 * Shows the data of the selected node.
	 */
	private void showSelectedNode() {
		taskList.setCurrentTaskPath(view.getCurrentTaskPath());
		currentTask = taskList.getCurrentTask();
		view.initFields(currentTask);
	}

	/**
	 * Calculates the current state of timer.
	 */
	private void adjustTime() {
		int minutes = view.getMinutes();
		int hours		= view.getHours();
		int days		= view.getDays();

		minutes++;
		if (minutes == IConstants.MAX_MINUTES) {
			minutes = 0;
			hours++;
			if (hours == IConstants.MAX_HOURS) {
				hours = 0;
				days++;
			}
		}
			
		view.setTime(minutes, hours, days);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		timer.stop();
		view.saveSurrentTaskState(currentTask);
		view.setComponentsState(true);
		DataStore.saveObject(IConstants.DEFAULT_FILE_PATH, taskList);
	}

	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		view.saveSurrentTaskState(currentTask);
		taskList.setCurrentTaskPath(e.getNewLeadSelectionPath());
		showSelectedNode();
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowActivated(WindowEvent e) {}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosed(WindowEvent e) {}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowIconified(WindowEvent e) {}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowOpened(WindowEvent e) {}

}