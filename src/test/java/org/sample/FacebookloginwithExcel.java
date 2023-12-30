package org.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FacebookloginwithExcel {

	public static void browser1(String emailid, String password) {
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();

		WebElement id = driver.findElement(By.id("email"));
		id.sendKeys(emailid);

		WebElement password1 = driver.findElement(By.id("pass"));
		password1.sendKeys(password);

	}

	public static void main(String[] args) throws IOException {
		File filepath = new File("D:\\Deepak\\excel\\stu_data.xlsx");

		FileInputStream stream = new FileInputStream(filepath);

		Workbook book = new XSSFWorkbook(stream);

		Sheet sheet = book.getSheet("Sheet3");

		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {

			String name = "", pass = "";
			Row row = sheet.getRow(i);

			for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {

				Cell cell = row.getCell(j);
				if (j == 0) {
					name = cell.getStringCellValue();

				} else {

					pass = cell.getStringCellValue();
				}

			}
			browser1(name, pass);

		}

	}

}
