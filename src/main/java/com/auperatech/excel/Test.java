/**
 * 
 */
package com.auperatech.excel;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.charts.ChartDataSource;
import org.apache.poi.ss.usermodel.charts.DataSources;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author Aupera
 *
 */
public class Test {
	public static void main(String[] args) throws IOException {
//		OneSheetExcel excel = new OneSheetExcel("test.xlsx", true);
//		/****************************initial some Data*******************************/
//		final int NUM_OF_ROWS = 3;
//        final int NUM_OF_COLUMNS = 10;
//        /*
//        // Create a row and put some cells in it. Rows are 0 based.
//        Row row;
//        Cell cell;
//        for (int rowIndex = 0; rowIndex < NUM_OF_ROWS; rowIndex++) {
//            row = excel.getMainSheet().createRow((short) rowIndex);
//            for (int colIndex = 0; colIndex < NUM_OF_COLUMNS; colIndex++) {
//                cell = row.createCell((short) colIndex);
//                cell.setCellValue(colIndex * (rowIndex + 1));
//            }
//        }*/
//        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(excel.getMainSheet(), new CellRangeAddress(0, 0, 0, NUM_OF_COLUMNS - 1));
//        ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(excel.getMainSheet(), new CellRangeAddress(1, 1, 0, NUM_OF_COLUMNS - 1));
//        ChartDataSource<Number> ys2 = DataSources.fromNumericCellRange(excel.getMainSheet(), new CellRangeAddress(2, 2, 0, NUM_OF_COLUMNS - 1));
//        /*******************************create a chart*********************************/
//        ExcelScatterChart chart01 = new  ExcelScatterChart(excel.getMainSheet());
//        chart01.addChartTitle("Sheet1!$A$1");
//        chart01.addSeries("TEST 01", xs, ys1);
//        chart01.addSeries("TEST 01", xs, ys2);
//        chart01.setBottomAxisTitle("real bitrate");
//        chart01.setLeftAxisTitle("PSNR(dB)");
//        chart01.plotToSheet();
//        ExcelScatterChart chart02 = new  ExcelScatterChart(excel.getMainSheet(), 0,0,0,0,10,5,20,20);
//        chart02.addChartTitle("TEST TITLE");
//        chart02.addSeries("TEST 01", xs, ys1);
//        chart02.addSeries("TEST 01", xs, ys2);
//        chart02.plotToSheet();
//        excel.saveToFile();
//        System.out.println(excel.getElementByPos(1, 'E'-'A'+1).getNumericCellValue());
		OneSheetExcel excel = new OneSheetExcel("Result.xlsx", true);
		System.out.println(excel.getElementByPos(3, 1).getStringCellValue());
		for(int i=0; i<20; i++) {
			ExcelScatterChart chart01 = new  ExcelScatterChart(excel.getMainSheet(),0,0,0,0,3+15*(i%5), 46+20*(i/5), 18+15*(i%5), 66+20*(i/5));
			ChartDataSource<Number> xs1 = DataSources.fromNumericCellRange(excel.getMainSheet(), new CellRangeAddress(2+i, 2+i, 8, 13));
			ChartDataSource<Number> xs2 = DataSources.fromNumericCellRange(excel.getMainSheet(), new CellRangeAddress(2+i, 2+i, 20, 25));
			ChartDataSource<Number> xs3 = DataSources.fromNumericCellRange(excel.getMainSheet(), new CellRangeAddress(24+i, 24+i, 8, 13));
			ChartDataSource<Number> xs4 = DataSources.fromNumericCellRange(excel.getMainSheet(), new CellRangeAddress(24+i, 24+i, 20, 25));
	        ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(excel.getMainSheet(), new CellRangeAddress(2+i, 2+i, 14, 19));
	        ChartDataSource<Number> ys2 = DataSources.fromNumericCellRange(excel.getMainSheet(), new CellRangeAddress(2+i, 2+i, 26, 31));
	        ChartDataSource<Number> ys3 = DataSources.fromNumericCellRange(excel.getMainSheet(), new CellRangeAddress(24+i, 24+i, 14, 19));
	        ChartDataSource<Number> ys4 = DataSources.fromNumericCellRange(excel.getMainSheet(), new CellRangeAddress(24+i, 24+i, 26, 31));
	        chart01.addChartTitle(excel.getElementByPos(3+i, 'B'-'A').getStringCellValue());
	        //chart01.addSeries("v205_uniform", xs1, ys1);
	        //chart01.addSeries("v205_adaptive_with_scenecut", xs2, ys2);
	        chart01.addSeries("x265", xs3, ys3);
	        chart01.addSeries("v205_adaptive_no_scenecut", xs4, ys4);
	        chart01.setLeftRange(Math.floor(excel.getElementByPos(25+i, 14).getNumericCellValue()-2),Math.ceil(excel.getElementByPos(25+i, 18).getNumericCellValue()+1));
	        chart01.setBottomAxisTitle("Real Bitrate(Kbps)");
	        chart01.setLeftAxisTitle("PSNR(dB)");
	        chart01.enableGrid();
	        chart01.plotToSheet();
		}
		excel.saveToFile();
	}
}
