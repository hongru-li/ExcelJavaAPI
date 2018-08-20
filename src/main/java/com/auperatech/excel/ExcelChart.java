/**
 * 
 */
package com.auperatech.excel;

import java.awt.Color;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.charts.AxisCrosses;
import org.apache.poi.ss.usermodel.charts.AxisPosition;
import org.apache.poi.ss.usermodel.charts.ChartAxis;
import org.apache.poi.ss.usermodel.charts.ChartLegend;
import org.apache.poi.ss.usermodel.charts.LegendPosition;
import org.apache.poi.ss.usermodel.charts.ValueAxis;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.*;

/**
 * @author lhrotk
 *
 */
public abstract class ExcelChart {
	protected XSSFChart chart;
	protected ValueAxis bottomAxis;
	protected ValueAxis leftAxis;
	/**
	 * Using default position and size
	 * @param sheet Excel sheet objects
	 */
	public ExcelChart(Sheet sheet) {
		 Drawing drawing = sheet.createDrawingPatriarch();
	     ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 20);
	     this.chart = (XSSFChart) drawing.createChart(anchor);
	     this.bottomAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.BOTTOM);
	     this.leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
	     leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
	     ChartLegend legend = chart.getOrCreateLegend();
	     legend.setPosition(LegendPosition.TOP_RIGHT);
	}
	
	public ExcelChart(Sheet sheet, int dx1, int dy1, int dx2, int dy2, int col1, int row1, int col2, int row2) {
		 Drawing drawing = sheet.createDrawingPatriarch();
	     ClientAnchor anchor = drawing.createAnchor(dx1, dy1, dx2, dy2, col1, row1, col2, row2);
	     this.chart = (XSSFChart) drawing.createChart(anchor);
	     this.bottomAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.BOTTOM);
	     this.leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
	     leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
	     ChartLegend legend = chart.getOrCreateLegend();
	     legend.setPosition(LegendPosition.RIGHT);
	}
	
	public void addChartTitle(String formula) {
		chart.setTitle(formula);
		chart.getCTChart().getTitle().addNewOverlay().setVal(false);
	}
	
	public abstract void plotToSheet(); 
	
	public void setCatAxisTitle(XSSFChart chart, int axisIdx, String title) {
	    CTCatAx valAx = chart.getCTChart().getPlotArea().getCatAxArray(axisIdx);
	    CTTitle ctTitle = valAx.addNewTitle();
	    ctTitle.addNewLayout();
	    ctTitle.addNewOverlay().setVal(false);
	    CTTextBody rich = ctTitle.addNewTx().addNewRich();
	    rich.addNewBodyPr();
	    rich.addNewLstStyle();
	    CTTextParagraph p = rich.addNewP();
	    p.addNewPPr().addNewDefRPr();
	    p.addNewR().setT(title);
	    p.addNewEndParaRPr();
	}


	public void setValueAxisTitle(XSSFChart chart, int axisIdx, String title) {
	    CTValAx valAx = chart.getCTChart().getPlotArea().getValAxArray(axisIdx);
	    CTTitle ctTitle = valAx.addNewTitle();
	    ctTitle.addNewLayout();
	    ctTitle.addNewOverlay().setVal(false);
	    CTTextBody rich = ctTitle.addNewTx().addNewRich();
	    rich.addNewBodyPr();
	    rich.addNewLstStyle();
	    CTTextParagraph p = rich.addNewP();
	    p.addNewPPr().addNewDefRPr();
	    p.addNewR().setT(title);
	    p.addNewEndParaRPr();
	}
	
	public void setLeftAxisTitle(String title) {
		this.setValueAxisTitle(chart, 1, title);
	}
	
	public void setBottomAxisTitle(String title) {
		this.setValueAxisTitle(chart, 0, title);
	}
	
	public void enableGrid() {
		Color col1 = new Color(200,200,200);
		CTSRgbColor color = CTSRgbColor.Factory.newInstance();
		color.setVal(new byte[] {(byte) col1.getRed(),(byte) col1.getGreen(),(byte)col1.getBlue()});
		CTChartLines lines = chart.getCTChart().getPlotArea().getValAxArray(1).addNewMajorGridlines();
		CTSolidColorFillProperties fillProp = CTSolidColorFillProperties.Factory.newInstance();
		fillProp.setSrgbClr(color);
		CTLineProperties lineProp = CTLineProperties.Factory.newInstance();
		lineProp.setSolidFill(fillProp);
		CTShapeProperties ctShapeProperties = CTShapeProperties.Factory.newInstance();
		ctShapeProperties.setLn(lineProp);
		lines.setSpPr(ctShapeProperties);
	}
	
	public void setLeftRange(double min, double max) {
		this.leftAxis.setMinimum(min);
		this.leftAxis.setMaximum(max);
	}
	
}
