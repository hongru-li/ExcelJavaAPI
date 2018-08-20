/**
 * 
 */
package com.auperatech.excel;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

/**
 * @author lhrotk
 *
 */
public class Test02 {
	public static void main(String[] args) throws IOException {
		Properties props = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream("prop"));
		props.load(in);
		System.out.println(props.getProperty("path"));
	}
}
