package com.aupetech.VQ_data_process;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFChartSheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class ApachePOIExcelRead {
	public static void setAxisTitle(XSSFChart chart, int axisIdx, String title) {
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

	public static void main(String[] args) throws Exception {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Sheet 1");
        final int NUM_OF_ROWS = 3;
        final int NUM_OF_COLUMNS = 10;
 
        // Create a row and put some cells in it. Rows are 0 based.
        Row row;
        Cell cell;
        for (int rowIndex = 0; rowIndex < NUM_OF_ROWS; rowIndex++) {
            row = sheet.createRow((short) rowIndex);
            for (int colIndex = 0; colIndex < NUM_OF_COLUMNS; colIndex++) {
                cell = row.createCell((short) colIndex);
                cell.setCellValue(colIndex * (rowIndex + 1));
            }
        }
 
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 50);
 
        XSSFChart chart = (XSSFChart) drawing.createChart(anchor);
        ChartLegend legend = chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);
 
        ScatterChartData data = chart.getChartDataFactory().createScatterChartData();
 
        ValueAxis bottomAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
        
 
        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(0, 0, 0, NUM_OF_COLUMNS - 1));
        ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(1, 1, 0, NUM_OF_COLUMNS - 1));
        ChartDataSource<Number> ys2 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(2, 2, 0, NUM_OF_COLUMNS - 1));
 
        
        data.addSerie(xs, ys1);
        data.addSerie(xs, ys2);
        
        setAxisTitle(chart, (int)bottomAxis.getId(), "hehe");
        chart.setTitle("test001");
 
        chart.plot(data, bottomAxis, leftAxis);
 
        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("ooxml-scatter-chart.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }

}
