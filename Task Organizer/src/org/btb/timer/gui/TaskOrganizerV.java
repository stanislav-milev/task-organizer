package org.btb.timer.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * @author Stanislav Milev
 * @created on Oct 2, 2013
 */
public class TaskOrganizerV extends JFrame {

	private static final long serialVersionUID = -4124243000720837663L;

	private static final int INSET = 2;
	
	private JButton btnNewTask;
	private JPanel pnlTasks;
	
	private int initialNumberOfTasks = 0;

	/**
	 * Constructor.
	 */
	public TaskOrganizerV(int initialNumberOfTasks) {
		this.initialNumberOfTasks = initialNumberOfTasks;
	}

	/**
	 * Builds the GUI.
	 */
	public void initGui() {
		this.setTitle("Task Organizer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		Container fcp = this.getContentPane();
		fcp.setLayout(new BorderLayout());
		
		btnNewTask = new JButton("New Task");
		btnNewTask.setPreferredSize(new Dimension(70, 25));
		fcp.add(btnNewTask, BorderLayout.LINE_END);
		
		pnlTasks = new JPanel();
		pnlTasks.setLayout(new GridBagLayout());
		JScrollPane spnTasks = new JScrollPane(pnlTasks);
		spnTasks.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		fcp.add(spnTasks, BorderLayout.PAGE_END);

		for (int i = 0; i < initialNumberOfTasks; i++) {
			addTask(new TaskPanelV());
		}
		
		this.pack();
		this.setVisible(true);
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth())/2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight())/2;
		this.setLocation(x, y);
	}

	/**
	 * Adds a Task Panel to the task list.
	 * @param newTask a new task
	 */
	public void addTask(TaskPanelV newTask) {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(INSET, INSET, INSET, INSET);
		c.gridx = 0;
		c.gridy = pnlTasks.getComponentCount();
		pnlTasks.add(newTask, c);
	}

	/**
	 * @return the btnNewTask
	 */
	public JButton getBtnNewTask() {
		return btnNewTask;
	}

}
