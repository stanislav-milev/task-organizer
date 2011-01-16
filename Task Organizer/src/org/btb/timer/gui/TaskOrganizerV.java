package org.btb.timer.gui;

import java.awt.Container;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.btb.timer.core.TaskO;

/**
 * Task Organizer view.
 * @author Stanislav Milev
 * @date Tue Sep 02 23:25:16 EEST 2008
 */
public class TaskOrganizerV extends JFrame implements IFrame {
	
	private static final long serialVersionUID = 2900548502443767184L;

	/* (non-Javadoc)
	 * @see org.btb.to.gui.IFrame#initGUI()
	 */
	public void initGUI() {
		this.setTitle("Task Organizer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container frameContentPane = this.getContentPane();
//		frameContentPane.setLayout();
		
		
		this.pack();
		this.setVisible(true);
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth())/2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight())/2;
		this.setLocation(x, y);
	}
	/**
	private void initComponents() {
		frame = new JFrame();
		lblTaskName = new JLabel();
		txfTaskName = new JTextField();
		lblDays = new JLabel();
		lblHours = new JLabel();
		lblMinutes = new JLabel();
		spnDays = new JSpinner();
		spnHours = new JSpinner();
		spnMinutes = new JSpinner();
		btnStart = new JButton();
		btnStop = new JButton();
		btnReset = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== frame ========
		{
			frameContentPane.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
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
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC
				}));
			((FormLayout)frameContentPane.getLayout()).setColumnGroups(new int[][] {{3, 5, 7}});
			
			//---- lblTaskName ----
			lblTaskName.setText("Task name:");
			frameContentPane.add(lblTaskName, cc.xywh(3, 3, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			frameContentPane.add(txfTaskName, cc.xywh(5, 3, 3, 1));
			
			//---- lblDays ----
			lblDays.setText("Days");
			frameContentPane.add(lblDays, cc.xywh(3, 5, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			
			//---- lblHours ----
			lblHours.setText("Hours");
			frameContentPane.add(lblHours, cc.xywh(5, 5, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			
			//---- lblMinutes ----
			lblMinutes.setText("Minutes");
			frameContentPane.add(lblMinutes, cc.xywh(7, 5, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			
			//---- spnDays ----
			spnDays.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			frameContentPane.add(spnDays, cc.xy(3, 7));
			
			//---- spnHours ----
			spnHours.setModel(new SpinnerNumberModel(0, 0, 23, 1));
			frameContentPane.add(spnHours, cc.xy(5, 7));
			
			//---- spnMinutes ----
			spnMinutes.setModel(new SpinnerNumberModel(0, 0, 59, 1));
			frameContentPane.add(spnMinutes, cc.xy(7, 7));
			
			//---- btnStart ----
			btnStart.setText("Start");
			frameContentPane.add(btnStart, cc.xy(3, 9));
			
			//---- btnStop ----
			btnStop.setText("Stop");
			frameContentPane.add(btnStop, cc.xy(5, 9));
			
			//---- btnReset ----
			btnReset.setText("Reset");
			frameContentPane.add(btnReset, cc.xy(7, 9));
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
	}
**/
	public String getTaskName() {
		return txfTaskName.getText();
	}

	public int getHours() {
		return Integer.parseInt(spnHours.getValue().toString());
	}

	public int getMinutes() {
		return Integer.parseInt(spnMinutes.getValue().toString());
	}

	public JButton getBtnStop() {
		return btnStop;
	}

	public JButton getBtnReset() {
		return btnReset;
	}

	public int getDays() {
		return Integer.parseInt(spnDays.getValue().toString());
	}

	public JButton getBtnStart() {
		return btnStart;
	}

	public JFrame gerFrame() {
		return frame;
	}
	
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JFrame frame;
	private JLabel lblTaskName;
	private JTextField txfTaskName;
	private JLabel lblDays;
	private JLabel lblHours;
	private JLabel lblMinutes;
	private JSpinner spnDays;
	private JSpinner spnHours;
	private JSpinner spnMinutes;
	private JButton btnStart;
	private JButton btnStop;
	private JButton btnReset;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	/**
	 * Disables or enables specific components.
	 * @param isTimerStopped
	 * @see org.btb.to.gui.IFrame#setComponentsState(boolean)
	 */
	@Override
	public void setComponentsState(boolean isTimerStopped) {
		txfTaskName.setEditable(isTimerStopped);
		spnDays.setEnabled(isTimerStopped);
		spnHours.setEnabled(isTimerStopped);
		spnMinutes.setEnabled(isTimerStopped);
		btnReset.setEnabled(isTimerStopped);
		btnStart.setEnabled(isTimerStopped);
		btnStop.setEnabled(!isTimerStopped);
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
	 * @return
	 */
	public TaskO getTimerO() {
		return new TaskO(getTaskName(), getMinutes(), getHours(), getDays());
	}

	/**
	 * Sets values in components.
	 * @param timerO
	 */
	public void initFields(TaskO timerO) {
		txfTaskName.setText(timerO.getTaskName());
		spnMinutes.setValue(new Integer(timerO.getMinutes()));
		spnHours.setValue(new Integer(timerO.getHours()));
		spnDays.setValue(new Integer(timerO.getDays()));
	}

}
