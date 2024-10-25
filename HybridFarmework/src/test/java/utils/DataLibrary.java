package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import base.ProgramExecution;

public class DataLibrary extends ProgramExecution{

	public static String[][] readExcel(String fileName) throws IOException {

		// Get the Excel workbook path
		XSSFWorkbook wb = new XSSFWorkbook("./ExcelFiles/"+fileName+".xlsx");

		// Get the sheet
		XSSFSheet sheet = wb.getSheetAt(0);

		// Get row count
		int rowSize = sheet.getLastRowNum();

		// Get column count
		int columnSize = sheet.getRow(0).getLastCellNum();

		// declare a 2d Array String as data
		String[][] data = new String[rowSize][columnSize];

		// iterate the row 1st
		for (int i = 1; i <= rowSize; i++) {
			XSSFRow row2 = sheet.getRow(i);
			// iterate the column
			for (int j = 0; j < columnSize; j++) {
				XSSFCell column2 = row2.getCell(j);
				String stringCellValue = column2.getStringCellValue();
				System.out.println(stringCellValue);
				data[i - 1][j] = stringCellValue; // 0, 0
			}
		}
		
		// Close excel workbook
		wb.close();

		return data;

	}
}
