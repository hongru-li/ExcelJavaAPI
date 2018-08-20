/**
 * 
 */
package com.aupetech.VQ_data_process;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author lhrotk
 *
 */
public class ChartLine {
	String lineName;
	private PriorityQueue<ChartPoint> line;
	private HashMap<String, ChartPoint> nameMap;
	public ChartLine(String lineName) {
		Comparator<ChartPoint> OrderIsdn =  new Comparator<ChartPoint>(){
			public int compare(ChartPoint p1, ChartPoint p2) {
				// TODO Auto-generated method stub
				double numbera = p1.getX();
				double numberb = p2.getX();
				if(numberb > numbera)
				{
					return -1;
				}
				else if(numberb<numbera)
				{
					return 1;
				}
				else
				{
					return 0;
				}
			
			}
		};
		this.line = new PriorityQueue<ChartPoint>(OrderIsdn);
		this.nameMap = new HashMap<String, ChartPoint>();
		this.lineName = lineName;
	}
	/**
	 * The queue is emptied after this function is called
	 * @return a list of {x,y,x,y,x,y.......}
	 */
	public List<Double> getData() {
		List<Double> result = new ArrayList<Double>();
		int counter = 0;
		while(!this.line.isEmpty()) {
			ChartPoint point = this.line.poll();
			result.add(point.getX());
			result.add(point.getY());
		}
		return result;
	} 
	
	public void addNewPointX(String pointName, double xValue) {
		if(this.nameMap.containsKey(pointName))
			return;
		ChartPoint newPoint = new ChartPoint();
		newPoint.setX(xValue);
		newPoint.setPointName(pointName);
		this.line.add(newPoint);
		this.nameMap.put(pointName, newPoint);
	}
	
	/**
	 * must first set the x points first
	 * @param pointName
	 * @param yValue
	 */
	public void addNewPointY(String pointName, double yValue) {
		if(!this.nameMap.containsKey(pointName)) {
			return;
		}
		this.nameMap.get(pointName).setY(yValue);
	}
}
