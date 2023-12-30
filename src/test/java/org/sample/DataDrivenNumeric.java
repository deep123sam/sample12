package org.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenNumeric {

	public static void main(String[] args) throws IOException {

		File filepath = new File("D:\\Deepak\\excel\\stu_data.xlsx");

		FileInputStream inputfile = new FileInputStream(filepath);

		Workbook book = new XSSFWorkbook(inputfile);

		Sheet sheet2 = book.getSheet("Sheet2");

		System.out.println("--------------------------------------------------------");

		for (int i = 0, x = 0; i < sheet2.getPhysicalNumberOfRows(); i++, x++) {

			Row row = sheet2.getRow(i);
			for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
				Cell cell = row.getCell(j);
				CellType cellType = cell.getCellType();

				switch (cellType) {
				case NUMERIC:

					if (DateUtil.isCellInternalDateFormatted(cell)) {
						Date date = cell.getDateCellValue();
						SimpleDateFormat dateformat = new SimpleDateFormat("dd/MMM/yyyy");
						String format = dateformat.format(date);
						System.out.print(format+"\t");

					} else {
						double numericCellValue = cell.getNumericCellValue();
						long valueof=(long)numericCellValue;
						//BigDecimal valueOf = BigDecimal.valueOf(numericCellValue);
						 
						System.out.print(valueof + "\t");

					}

					break;
				case STRING:

					String stringCellValue = cell.getStringCellValue();
					System.out.print(stringCellValue + "\t");
					
					break;

				default:
					System.out.print("None");
					break;
				}

			}
			if (x == 0) {
				System.out.println("\n--------------------------------------------------------");

			}
			System.out.println();
		}

	}

}
