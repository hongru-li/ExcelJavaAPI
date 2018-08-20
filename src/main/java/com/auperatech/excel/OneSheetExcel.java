/**
 * 
 */
package com.auperatech.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;

/**
 * @author lhrotk
 *
 */
public class OneSheetExcel {
	private Workbook wb;
	private Sheet mainSheet;
	private String name;
	private int rowCounter=1;
	private Row row;
	private Cell cell;
	private XSSFCellStyle my_style_1;

	/**
	 * @return the rowCounter
	 */
	public int getRowCounter() {
		return rowCounter;
	}

	/**
	 * @param rowCounter the rowCounter to set
	 */
	public void setRowCounter(int rowCounter) {
		this.rowCounter = rowCounter;
	}

	/**
	 * create new excel and
	 */
	public OneSheetExcel() {
		wb = new XSSFWorkbook();
		mainSheet = wb.createSheet("Sheet 1");
		this.name = "sheet1.xlsx";
		this.my_style_1 = (XSSFCellStyle) wb.createCellStyle();
		my_style_1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        my_style_1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	}

	public OneSheetExcel(String fileName, boolean isExist) {
		this.name = fileName;
		if (isExist) {
			try {
				FileInputStream excelFile = new FileInputStream(new File(fileName));
				this.wb = new XSSFWorkbook(excelFile);
				this.mainSheet = wb.getSheetAt(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			wb = new XSSFWorkbook();
			mainSheet = wb.createSheet("Sheet 1");
			this.name = fileName;
		}
		this.my_style_1 = (XSSFCellStyle) wb.createCellStyle();
		my_style_1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        my_style_1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	}
	
	

	/**
	 * @return the wb
	 */
	public Workbook getWb() {
		return wb;
	}

	/**
	 * @return the mainSheet
	 */
	public Sheet getMainSheet() {
		return mainSheet;
	}
	
	public void saveToFile() throws IOException {
		FileOutputStream fileOut = new FileOutputStream(this.name);
        wb.write(fileOut);
        fileOut.close();
	}
	
	public Cell getElementByPos(int row, int col) {
		if(row<=0||col<=0)
			return null;
		Row currentRow = null;
		Iterator<Row> iteRow = this.mainSheet.iterator();
		for(int i=1; i<=row; i++) {
			if(iteRow.hasNext()) {
				currentRow = iteRow.next();
			}else {
				return null;
			}
		}
		Cell target = null;
		Iterator<Cell> iteCol = currentRow.cellIterator();
		for(int i=1; i<=col; i++) {
			if(iteCol.hasNext()) {
				 target = iteCol.next();
			}else {
				return null;
			}
		}
		return target;
	}
	
	public void setString(String content, short rowNumber, short colNumber) {
		this.row = this.mainSheet.getRow(rowNumber);
		this.cell = this.row.createCell(colNumber);
		this.cell.setCellStyle(my_style_1);
		this.cell.setCellValue(content);
	}
	
	public void setString(double content, short rowNumber, short colNumber) {
		this.row = this.mainSheet.getRow(rowNumber);
		this.cell = this.row.createCell(colNumber);
		this.cell.setCellStyle(my_style_1);
		this.cell.setCellValue(content);
	}
	
	public void createArea(int numberOfRows) {
		for(short i=0; i<numberOfRows; i++) {
			this.mainSheet.createRow(this.rowCounter+i);
		}
	}
	
	public String getWindowFileName(String path) {
		String[] names = path.split("\\\\");
		return names[names.length-1];
	}
	
	
}
