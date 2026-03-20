package com.testing.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.*;

public class ExcelReader {
	public static Object[][] excelDataReader(int l) throws IOException{
		InputStream fis = ExcelReader.class
	            .getClassLoader()
	            .getResourceAsStream("DataRead.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		int rowCount = sheet.getLastRowNum();
		int colCount = sheet.getRow(0).getLastCellNum();
		System.out.println(rowCount+" "+colCount);
		 Object[][] data = new Object[rowCount - l + 1][colCount];
		
		 for (int i = l; i <= rowCount; i++) {
		        XSSFRow row = sheet.getRow(i);

		        for (int j = 0; j < colCount; j++) {
		            if (row == null) {
		                data[i - l][j] = "";
		                continue;
		            }

		            XSSFCell cell = row.getCell(j);
		            data[i - l][j] = (cell == null) ? "" : cell.toString();
		        }
		    }
		
		workbook.close();
		return data;
	}
	
	public static Object[][] excelDataReader(int l, int r) throws IOException{
		InputStream fis = ExcelReader.class
	            .getClassLoader()
	            .getResourceAsStream("DataRead.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		int rowCount = sheet.getLastRowNum();
		int colCount = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rowCount][colCount];
		
		for(int i=l;i<=r;i++) {
			XSSFRow row = sheet.getRow(i);
			for(int j=0;j<colCount;j++) {
				XSSFCell cell = row.getCell(j);
				data[i-1][j]=cell.toString();
			}
		}
		
		workbook.close();
		return data;
	}
}
