/**
 * 
 */
package com.aupetech.VQ_data_process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aupera
 *
 */
public class ChartMaker {
	List<ChartData> charts;
	List<File> subDirs;
	public ChartMaker() {
		this.charts = new ArrayList<ChartData>();
	}
	
	public boolean stringMask(String name, String[] masks) {
		for(int i=0; i<masks.length; i++) {
			String[] names = name.split("\\\\");
			if(names[names.length-1].equals(masks[i])) {
				return true;
			}
		}
		return false;
	}
	
	public void setChartBase(String dictName, String[] masks) throws IOException {
		ChartData oneChart = new ChartData(dictName);
		File directory = new File(dictName);
		subDirs = new ArrayList<File>();
		File[] subFiles = directory.listFiles();
		for(int i=0; i<subFiles.length; i++) {
			if(subFiles[i].isDirectory()&&!this.stringMask(subFiles[i].getPath(), masks)) {
				BufferedReader bf;
				try {
					File bitrateRec = new File(subFiles[i].getPath()+"\\bitrate.csv");
					bf = new BufferedReader(new FileReader(bitrateRec));
				}catch(Exception e) {
					continue;
				}
				String buffer;
				while((buffer = bf.readLine())!=null) {
					String[] data = buffer.split(",");
					if(!(data[0].equals("")||data[0].split("\\.")[1].equals("csv"))) {
						String[] Path = subFiles[i].getName().split("\\\\");
						oneChart.addNewPointX(Path[Path.length-1], data[0], Double.valueOf(data[1]));
					}
				}
			}
		}
		File average = new File(directory.getPath()+"\\average.csv");
		BufferedReader bf = new BufferedReader(new FileReader(average));
		bf.readLine();
		String buffer;
		while((buffer = bf.readLine())!=null) {
			String[] content = buffer.split(",");
			//double yValue = (Double.valueOf(content[2])*6+Double.valueOf(content[3])+Double.valueOf(content[4]))/8;
			double yValue = (Double.valueOf(content[2]));
			String[] seriesPath = content[1].split("/");
			oneChart.addNewPointY(seriesPath[0], seriesPath[1], yValue);
		}
		/*ChartLine oneLine = oneChart.getLines().get(1);
		List<Double> points = oneLine.getData();
		for(int i=0; i<points.size(); i++) {
			System.out.print(points.get(i)+" ");
		}*/
		charts.add(oneChart);
	}
}
