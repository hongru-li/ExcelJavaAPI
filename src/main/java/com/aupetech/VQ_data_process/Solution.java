package com.aupetech.VQ_data_process;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.charts.ChartDataSource;
import org.apache.poi.ss.usermodel.charts.DataSources;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import com.auperatech.excel.ExcelScatterChart;
import com.auperatech.excel.OneSheetExcel;

public class Solution {
	public static void main(String[] args) throws IOException {
		ChartMaker chartMaker;
		/*******************************
		 * load config
		 * ******************************/
		Properties props = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream("prop"));
		props.load(in);
		String path = props.getProperty("path");
		String output = props.getProperty("output","summary.xslx");
		String[] masks = props.getProperty("masks").split(";");
		/*****************************
		 * create the sheet
		 ***********************************/
		OneSheetExcel excel = new OneSheetExcel(output, false);
		/*****************************
		 * print data to sheet
		 *********************************/
		chartMaker = new ChartMaker();
		try {
			File base = new File(path);
			File[] folders = base.listFiles();
			for (int i = 0; i < folders.length; i++) {
				if (folders[i].isDirectory()) {
					chartMaker.setChartBase(folders[i].getPath(), masks);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int counter = 0; counter < chartMaker.charts.size(); counter++) {
			ChartData chartData = chartMaker.charts.get(counter);
			excel.createArea(10);
			excel.setString("Video", (short) excel.getRowCounter(), (short) 0);
			excel.setString(excel.getWindowFileName(chartData.chartName), (short) (excel.getRowCounter() + 1),
					(short) 0);
			excel.setString("Target Bitrate", (short) (excel.getRowCounter() + 1), (short) 1);
			excel.setString(500, (short) (excel.getRowCounter() + 2), (short) 1);
			excel.setString(1000, (short) (excel.getRowCounter() + 3), (short) 1);
			excel.setString(2000, (short) (excel.getRowCounter() + 4), (short) 1);
			excel.setString(3000, (short) (excel.getRowCounter() + 5), (short) 1);
			excel.setString(4000, (short) (excel.getRowCounter() + 6), (short) 1);
			excel.setString(5000, (short) (excel.getRowCounter() + 7), (short) 1);
			ExcelScatterChart chart01 = new ExcelScatterChart(excel.getMainSheet(), 0, 0, 0, 0,
					2 + 2 * chartData.lines.size(), excel.getRowCounter(), 2 + 2 * chartData.lines.size() + 10,
					excel.getRowCounter() + 16);
			double min = 500;
			double max = 0;
			for (int i = 0; i < chartData.lines.size(); i++) {
				short col = (short) (2 + 2 * i);
				ChartLine line = chartData.lines.get(i);
				excel.setString(line.lineName, (short) excel.getRowCounter(), (short) col);
				excel.getMainSheet().addMergedRegion(new CellRangeAddress((short) excel.getRowCounter(),
						(short) excel.getRowCounter(), col, col + 1));
				excel.setString("real bitrate", (short) (excel.getRowCounter() + 1), (short) col);
				excel.setString("PSNR", (short) (excel.getRowCounter() + 1), (short) (col + 1));
				List<Double> lineData = line.getData();
				for (int j = 0; 2 * j < lineData.size(); j++) {
					excel.setString(lineData.get(2 * j), (short) (excel.getRowCounter() + j + 2), col);
					excel.setString(lineData.get(2 * j + 1), (short) (excel.getRowCounter() + j + 2),
							(short) (col + 1));
				}
				min = Math.min(min, lineData.get(1));
				max = Math.max(max, lineData.get(lineData.size() - 1));
				ChartDataSource<Number> xs1 = DataSources.fromNumericCellRange(excel.getMainSheet(),
						new CellRangeAddress(excel.getRowCounter() + 2,
								excel.getRowCounter() + (lineData.size() - 1) / 2 + 2, col, col));
				ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(excel.getMainSheet(),
						new CellRangeAddress(excel.getRowCounter() + 2,
								excel.getRowCounter() + (lineData.size() - 1) / 2 + 2, col + 1, col + 1));
				chart01.addSeries(line.lineName, xs1, ys1);
			}
			chart01.addChartTitle(excel.getWindowFileName(chartData.chartName));
			chart01.setLeftRange(Math.floor(min) - 1, Math.ceil(max) + 1);
			chart01.setBottomAxisTitle("Real Bitrate(Kbps)");
			chart01.setLeftAxisTitle("PSNR(dB)");
			chart01.enableGrid();
			chart01.plotToSheet();
			excel.setRowCounter(excel.getRowCounter() + 17);
		}
		excel.saveToFile();
	}
}
