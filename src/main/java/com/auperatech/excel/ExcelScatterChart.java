/**
 * 
 */
package com.auperatech.excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.charts.ChartDataSource;
import org.apache.poi.ss.usermodel.charts.LineChartSeries;
import org.apache.poi.ss.usermodel.charts.ScatterChartData;
import org.apache.poi.ss.usermodel.charts.ScatterChartSeries;

/**
 * @author lhrotk
 *
 */
public class ExcelScatterChart extends ExcelChart{

	ScatterChartData data;
	/**
	 * @param sheet
	 */
	public ExcelScatterChart(Sheet sheet) {
		super(sheet);
		this.data = chart.getChartDataFactory().createScatterChartData();
	}
	
	public ExcelScatterChart(Sheet sheet,int dx1, int dy1, int dx2, int dy2, int col1, int row1, int col2, int row2) {
		super(sheet, dx1, dy1, dx2, dy2, col1, row1, col2, row2);
		this.data = chart.getChartDataFactory().createScatterChartData();
	}

	public void addSeries(String seriesName, ChartDataSource<Number> x, ChartDataSource<Number> y) {
		ScatterChartSeries chartSerie = this.data.addSerie(x, y);
		chartSerie.setTitle(seriesName);
	}

	@Override
	public void plotToSheet() {
		chart.plot(data, bottomAxis, leftAxis);
	}
}
