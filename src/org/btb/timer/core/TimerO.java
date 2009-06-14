package org.btb.timer.core;

import java.io.Serializable;


/**
 * Storage object for the timers data.
 * @author Stanislav Milev
 * @date 16.10.2008
 */
public class TimerO implements Serializable {

	private static final long serialVersionUID = 817955233404847856L;
	
	private String taskName;
	private int minutes;
	private int hours;
	private int days;
	
	/**
	 * Constructor.
	 */
	public TimerO() {
		taskName	= "";
		minutes		= 0;
		hours		= 0;
		days		= 0;
	}

	/**
	 * Constructor.
	 * @param taskName
	 * @param minutes
	 * @param hours
	 * @param days
	 */
	public TimerO(String taskName, int minutes, int hours, int days) {
		this.taskName = taskName;
		this.minutes = minutes;
		this.hours = hours;
		this.days = days;
	}

	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * @param minutes the minutes to set
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * @return the days
	 */
	public int getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(int days) {
		this.days = days;
	}

}
