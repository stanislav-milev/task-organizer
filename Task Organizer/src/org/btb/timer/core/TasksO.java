package org.btb.timer.core;

import java.io.Serializable;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * @author Stanislav Milev
 * @created on 21.06.2009
 */
public class TasksO implements Serializable {

	private static final long serialVersionUID = -5468822726800043886L;
	
	private DefaultMutableTreeNode tasksTree;
	private TreePath currentTaskPath;
	
	/**
	 * Constructor
	 */
	public TasksO() {
		tasksTree = new DefaultMutableTreeNode("Tasks");
		DefaultMutableTreeNode newTask = new DefaultMutableTreeNode(new TaskO("New Task"));
		tasksTree.add(newTask);
		currentTaskPath = new TreePath(newTask.getPath());
	}

	/**
	 * @return the tasksTree
	 */
	public DefaultMutableTreeNode getTasksTree() {
		return tasksTree;
	}

	/**
	 * @param tasksTree the tasksTree to set
	 */
	public void setTasksTree(DefaultMutableTreeNode tasksTree) {
		this.tasksTree = tasksTree;
	}

	/**
	 * @return the currentTaskPath
	 */
	public TreePath getCurrentTaskPath() {
		return currentTaskPath;
	}

	/**
	 * @param currentTaskPath the currentTaskPath to set
	 */
	public void setCurrentTaskPath(TreePath currentTaskPath) {
		this.currentTaskPath = currentTaskPath;
	}

	public TaskO getCurrentTask() {
		return (TaskO) ((DefaultMutableTreeNode) currentTaskPath.getLastPathComponent()).getUserObject();
	}

}
