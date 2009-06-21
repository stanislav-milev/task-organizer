package org.btb.timer.gui;

import java.awt.Container;
import java.awt.Toolkit;
import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.tree.*;

import org.btb.timer.core.TaskO;
import org.btb.timer.core.TasksO;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.*;


/**
 * Timers view.
 * @author Stanislav Milev
 * @date Tue Sep 02 23:25:16 EEST 2008
 */
public class TimerV implements IFrame {
	
	private static final long serialVersionUID = 2900548502443767184L;
	
	/* (non-Javadoc)
	 * @see org.btb.timer.gui.IFrame#initGUI()
	 */
	public void initGUI() {
		initComponents();
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		frame = new JFrame();
		pnlTaskControls = new JPanel();
		btnAddTask = new JButton();
		btnDeleteTask = new JButton();
		lblTaskName = new JLabel();
		txfTaskName = new JTextField();
		scpTaskList = new JScrollPane();
		treTaskList = new JTree();
		lblTaskDescription = new JLabel();
		scpTaskDescription = new JScrollPane();
		txaTaskDescription = new JTextArea();
		lblDays = new JLabel();
		lblHours = new JLabel();
		lblMinutes = new JLabel();
		spnDays = new JSpinner();
		spnHours = new JSpinner();
		spnMinutes = new JSpinner();
		btnStartTimer = new JButton();
		btnStopTimer = new JButton();
		btnResetTimer = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== frame ========
		{
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle("Task Organizer");
			frame.setResizable(false);
			Container frameContentPane = frame.getContentPane();
			frameContentPane.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(120)),
					FormFactory.UNRELATED_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(60)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.FILL, Sizes.dluY(100), FormSpec.NO_GROW),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW)
				}));
			((FormLayout)frameContentPane.getLayout()).setColumnGroups(new int[][] {{5, 7, 9}});
			
			//======== pnlTaskControls ========
			{
				pnlTaskControls.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC
					},
					RowSpec.decodeSpecs("default")));
				
				//---- btnAddTask ----
				btnAddTask.setText("Add");
				pnlTaskControls.add(btnAddTask, cc.xy(3, 1));
				
				//---- btnDeleteTask ----
				btnDeleteTask.setText("Delete");
				pnlTaskControls.add(btnDeleteTask, cc.xy(5, 1));
			}
			frameContentPane.add(pnlTaskControls, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- lblTaskName ----
			lblTaskName.setText("Task Name:");
			frameContentPane.add(lblTaskName, cc.xywh(5, 3, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			frameContentPane.add(txfTaskName, cc.xywh(7, 3, 3, 1));
			
			//======== scpTaskList ========
			{
				
				//---- treTaskList ----
				treTaskList.setEditable(true);
				treTaskList.setModel(new DefaultTreeModel(
					new DefaultMutableTreeNode("Tasks") {
						{
						}
					}));
				scpTaskList.setViewportView(treTaskList);
			}
			frameContentPane.add(scpTaskList, cc.xywh(3, 5, 1, 9));
			
			//---- lblTaskDescription ----
			lblTaskDescription.setText("Description:");
			frameContentPane.add(lblTaskDescription, cc.xywh(5, 5, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			
			//======== scpTaskDescription ========
			{
				scpTaskDescription.setViewportView(txaTaskDescription);
			}
			frameContentPane.add(scpTaskDescription, cc.xywh(5, 7, 5, 1));
			
			//---- lblDays ----
			lblDays.setText("Days");
			frameContentPane.add(lblDays, cc.xywh(5, 9, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			
			//---- lblHours ----
			lblHours.setText("Hours");
			frameContentPane.add(lblHours, cc.xywh(7, 9, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			
			//---- lblMinutes ----
			lblMinutes.setText("Minutes");
			frameContentPane.add(lblMinutes, cc.xywh(9, 9, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			
			//---- spnDays ----
			spnDays.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			frameContentPane.add(spnDays, cc.xy(5, 11));
			
			//---- spnHours ----
			spnHours.setModel(new SpinnerNumberModel(0, 0, 23, 1));
			frameContentPane.add(spnHours, cc.xy(7, 11));
			
			//---- spnMinutes ----
			spnMinutes.setModel(new SpinnerNumberModel(0, 0, 59, 1));
			frameContentPane.add(spnMinutes, cc.xy(9, 11));
			
			//---- btnStartTimer ----
			btnStartTimer.setText("Start");
			frameContentPane.add(btnStartTimer, cc.xy(5, 13));
			
			//---- btnStopTimer ----
			btnStopTimer.setText("Stop");
			frameContentPane.add(btnStopTimer, cc.xy(7, 13));
			
			//---- btnResetTimer ----
			btnResetTimer.setText("Reset");
			frameContentPane.add(btnResetTimer, cc.xy(9, 13));
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		frame.pack();
		frame.setVisible(true);
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 
				frame.getWidth())/2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 
				frame.getHeight())/2;
		frame.setLocation(x, y);
	}

	public String getTaskName() {
		return txfTaskName.getText();
	}

	public String getTaskDescription() {
		return txaTaskDescription.getText();
	}
	
	public int getHours() {
		return Integer.parseInt(spnHours.getValue().toString());
	}

	public int getMinutes() {
		return Integer.parseInt(spnMinutes.getValue().toString());
	}

	public int getDays() {
		return Integer.parseInt(spnDays.getValue().toString());
	}

	public JButton getBtnAddTask() {
		return btnAddTask;
	}

	public JButton getBtnDeleteTask() {
		return btnDeleteTask;
	}

	public JButton getBtnStartTimer() {
		return btnStartTimer;
	}

	public JButton getBtnStopTimer() {
		return btnStopTimer;
	}

	public JButton getBtnResetTimer() {
		return btnResetTimer;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JTree getTreTaskList() {
		return treTaskList;
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JFrame frame;
	private JPanel pnlTaskControls;
	private JButton btnAddTask;
	private JButton btnDeleteTask;
	private JLabel lblTaskName;
	private JTextField txfTaskName;
	private JScrollPane scpTaskList;
	private JTree treTaskList;
	private JLabel lblTaskDescription;
	private JScrollPane scpTaskDescription;
	private JTextArea txaTaskDescription;
	private JLabel lblDays;
	private JLabel lblHours;
	private JLabel lblMinutes;
	private JSpinner spnDays;
	private JSpinner spnHours;
	private JSpinner spnMinutes;
	private JButton btnStartTimer;
	private JButton btnStopTimer;
	private JButton btnResetTimer;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	/**
	 * Disables or enables specific components.
	 * @param isTimerStopped
	 * @see org.btb.timer.gui.IFrame#setComponentsState(boolean)
	 */
	@Override
	public void setComponentsState(boolean isTimerStopped) {
		txfTaskName.setEditable(isTimerStopped);
		spnDays.setEnabled(isTimerStopped);
		spnHours.setEnabled(isTimerStopped);
		spnMinutes.setEnabled(isTimerStopped);
		btnResetTimer.setEnabled(isTimerStopped);
		btnStartTimer.setEnabled(isTimerStopped);
		btnStopTimer.setEnabled(!isTimerStopped);
	}

	/**
	 * @param timerO
	 */
	public void setTime(int minutes, int hours, int days) {
		spnMinutes.setValue(new Integer(minutes));
		spnHours.setValue(new Integer(hours));
		spnDays.setValue(new Integer(days));
	}

	/**
	 * Sets values in components.
	 * @param taskO
	 */
	public void initFields(TaskO taskO) {
		txfTaskName.setText(taskO.getTaskName());
		txaTaskDescription.setText(taskO.getDescription());
		spnMinutes.setValue(new Integer(taskO.getMinutes()));
		spnHours.setValue(new Integer(taskO.getHours()));
		spnDays.setValue(new Integer(taskO.getDays()));
	}

	public void initOrganizer(TasksO tasksO) {
		treTaskList.setModel(new DefaultTreeModel(tasksO.getTasksTree()));
		treTaskList.setSelectionPath(tasksO.getCurrentTaskPath());
		initFields(tasksO.getCurrentTask());
	}

	public void addNewTaskNode() {
		// TODO Auto-generated method stub
		
	}
	
}
