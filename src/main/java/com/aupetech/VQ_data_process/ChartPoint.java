/**
 * 
 */
package com.aupetech.VQ_data_process;

/**
 * @author Aupera
 *
 */
public class ChartPoint {
	private double x;
	private double y;
	private String pointName;
	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * @return the pointName
	 */
	public String getPointName() {
		return pointName;
	}
	/**
	 * @param pointName the pointName to set
	 */
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public ChartPoint() {
		this.x = 0;
		this.y = 0;
	}
}
