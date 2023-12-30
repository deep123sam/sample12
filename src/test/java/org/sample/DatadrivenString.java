package org.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DatadrivenString {

	public static void main(String[] args) throws IOException {

		File filepath = new File("D:\\Deepak\\excel\\stu_data.xlsx");

		FileInputStream stream = new FileInputStream(filepath);

		Workbook book = new XSSFWorkbook(stream);

		Sheet sheet = book.getSheet("Sheet1");
		
		System.out.println("-----------------------------");

		for (int i = 0, x = 0; i < sheet.getPhysicalNumberOfRows(); i++, x++) {

			Row row = sheet.getRow(i);

			for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {

				Cell cell = row.getCell(j);
				String stringCellValue = cell.getStringCellValue();
				System.out.print(stringCellValue + "\t");

			}
			if (x == 0) {
				
				System.out.println("\n-----------------------------");

			}
			System.out.println();
		}

	}

}
