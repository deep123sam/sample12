package org.sample;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

	public static void main(String[] args) throws IOException {

		char c = 65,s=97;

		File filepath = new File("D:\\Deepak\\excel\\baseclass4.xlsx");
		Workbook book = new XSSFWorkbook();

		Sheet createSheet = book.createSheet("robot2");

		for (int i = 0; i < 10; i++) {
			Row createRow = createSheet.createRow(i);

			for (int j = 0; j < 1; j++, c++,s++) {
				Cell createCell = createRow.createCell(j);
				createCell.setCellValue("case '"+i+"':\nr.keyPress(KeyEvent.VK_"+i+");\nr.keyRelease(KeyEvent.VK_"+i+");\nbreak;\n");
				FileOutputStream outputstream = new FileOutputStream(filepath);
				book.write(outputstream);

			}

		}

	}

}
