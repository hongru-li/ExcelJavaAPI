/**
 * 
 */
package com.aupetech.VQ_data_process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author lhrotk
 *
 */
public class ChartData {
	String chartName;//use the directory name
	List<ChartLine> lines;
	HashMap<String, ChartLine> nameMap;
	public ChartData(String chartName) {
		this.chartName = chartName;
		this.nameMap = new HashMap<String, ChartLine>();
		this.lines = new ArrayList<ChartLine>();
	}
	
	public void addNewPointX(String seriesName, String pointName, double xValue) {
		if(!this.nameMap.containsKey(seriesName)) {
			ChartLine newSeries = new ChartLine(seriesName);
			this.nameMap.put(seriesName, newSeries);
			newSeries.addNewPointX(pointName, xValue);
			lines.add(newSeries);
		}else {
			this.nameMap.get(seriesName).addNewPointX(pointName, xValue);
		}
	}
	
	/**
	 * @return the lines
	 */
	public List<ChartLine> getLines() {
		return lines;
	}

	/**
	 * @param lines the lines to set
	 */
	public void setLines(List<ChartLine> lines) {
		this.lines = lines;
	}

	/**
	 * add point X series first
	 * @param seriesName codec name
	 * @param pointName video(target bitrate)
	 * @param yValue PSNR OR SSIM
	 */
	public void addNewPointY(String seriesName, String pointName, double yValue) {
		if(!this.nameMap.containsKey(seriesName)) {
			return;
		}else {
			this.nameMap.get(seriesName).addNewPointY(pointName, yValue);
		}
	}
}
