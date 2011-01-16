package org.btb.timer.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	
	/* (non-Javadoc)
	 * @see org.btb.to.gui.IFrame#initGUI()
	 */
	public void initGUI() {
		this.setTitle("Task Organizer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		Container fcp = this.getContentPane();
		fcp.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		lblTaskName = new JLabel("Task name:");
		c.gridx = 0;
		c.gridy = 0;
		fcp.add(lblTaskName, c);
		
		txfTaskName = new JTextField();
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		fcp.add(txfTaskName, c);
		c.gridwidth = 1;
		
		lblDays = new JLabel("Days");
		c.gridx = 0;
		c.gridy = 1;
		fcp.add(lblDays, c);

		lblHours = new JLabel("Hours");
		c.gridx = 1;
		c.gridy = 1;
		fcp.add(lblHours, c);

		lblMinutes = new JLabel("Minutes");
		c.gridx = 2;
		c.gridy = 1;
		fcp.add(lblMinutes, c);
		
		spnDays = new JSpinner();
		spnDays.setModel(new SpinnerNumberModel(0, 0, null, 1));
		c.gridx = 0;
		c.gridy = 2;
		fcp.add(spnDays, c);
		
		spnHours = new JSpinner();
		spnHours.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		c.gridx = 1;
		c.gridy = 2;
		fcp.add(spnHours, c);
		
		spnMinutes = new JSpinner();
		spnMinutes.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		c.gridx = 2;
		c.gridy = 2;
		fcp.add(spnMinutes, c);
		
		btnStart = new JButton("Start");
		c.gridx = 0;
		c.gridy = 3;
		fcp.add(btnStart, c);

		btnStop = new JButton("Stop");
		c.gridx = 1;
		c.gridy = 3;
		fcp.add(btnStop, c);

		btnReset = new JButton("Reset");
		c.gridx = 2;
		c.gridy = 3;
		fcp.add(btnReset, c);
		
		this.pack();
		this.setVisible(true);
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth())/2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight())/2;
		this.setLocation(x, y);
	}
	
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

}
