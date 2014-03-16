package org.btb.timer.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.btb.timer.util.Configuration;

/**
 * @author Stanislav Milev
 * @created on Oct 2, 2013
 */
public class TaskOrganizerV extends JFrame {

	private static final long serialVersionUID = -4124243000720837663L;

	private JButton btnNewTask;
	private JPanel pnlTasks;
	private JScrollPane spnTasks;
	
	private int maxDisplayedTasks;
	
	/**
	 * Constructor - Builds the GUI.
	 */
	public TaskOrganizerV() {
		maxDisplayedTasks = Configuration.getInstance().getMaxDisplayedTasks();
		this.setTitle("Task Organizer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		Container fcp = this.getContentPane();
		fcp.setLayout(new BorderLayout());
		
		btnNewTask = new JButton("New Task");
		btnNewTask.setPreferredSize(new Dimension(70, 25));
		fcp.add(btnNewTask, BorderLayout.LINE_END);
		
		pnlTasks = new JPanel();
		pnlTasks.setLayout(new GridLayout(0,1));
		spnTasks = new JScrollPane(pnlTasks);
		spnTasks.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		fcp.add(spnTasks, BorderLayout.PAGE_END);
		this.pack();
		pnlTasks = (JPanel) spnTasks.getViewport().getView();
		
		URL url = ClassLoader.getSystemResource("icon.png");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage(url);
		this.setIconImage(img);
	}

	/**
	 * Shows the UI.
	 */
	public void showTheUI() {
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth())/2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight())/2;
		this.setLocation(x, y);
		this.setVisible(true);
	}

	/**
	 * Adds a Task Panel to the task list.
	 * @param newTask a new task
	 */
	public void addTask(TaskPanelV newTask) {
		pnlTasks.add(newTask);
		if (pnlTasks.getComponentCount() <= maxDisplayedTasks) {
			spnTasks.setPreferredSize(null);
			this.pack();
			if (pnlTasks.getComponentCount() == maxDisplayedTasks) {
				spnTasks.setPreferredSize(spnTasks.getSize());
			}
		}
		this.pack();
		
		//if this is the second task, enable the remove button of the first one
		if (pnlTasks.getComponentCount() == 2) {
			TaskPanelV lastTask = (TaskPanelV) pnlTasks.getComponent(0);
			lastTask.getBtnDelete().setEnabled(true);
		}
	}

	/**
	 * Removes the given task.
	 * @param task for removal
	 */
	public void removeTask(TaskPanelV task) {
		pnlTasks.remove(task);
		if (pnlTasks.getComponentCount() < maxDisplayedTasks) {
			spnTasks.setPreferredSize(null);
			this.pack();
			spnTasks.setPreferredSize(spnTasks.getSize());
		}
		this.pack();
		
		//if one element has left, disable it's remove button
		if (pnlTasks.getComponentCount() == 1) {
			TaskPanelV lastTask = (TaskPanelV) pnlTasks.getComponent(0);
			lastTask.getBtnDelete().setEnabled(false);
		}
	}

	/**
	 * @return the btnNewTask
	 */
	public JButton getBtnNewTask() {
		return btnNewTask;
	}

}
