package org.base;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Set;
import java.awt.AWTException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;

	public static void getdriver(String browsertype) {

		switch (browsertype) {
		case "Chrome":

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

			break;

		case "Edge":

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

			break;

		case "Mozilla Firefox":

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

			break;

		default:

			System.out.println("Enter a Correct Browser Name:\n1.Chrome\n2.Edge\n3.Mozilla Firefox");
			break;
		}
	}

	public static void geturl(String url) {

		driver.get(url);
		driver.manage().window().maximize();
	}

	public void sendkeys(WebElement ref, String input) {

		ref.sendKeys(input);

	}

	public void navigate(String naviagte) {

		switch (naviagte) {
		case "forward":

			driver.navigate().forward();

			break;

		case "backward":

			driver.navigate().back();

			break;

		case "refresh":

			driver.navigate().refresh();

			break;

		default:

			System.out.println("Enter Correct Navigation Command\n1.forward\n2.backward\n3.refresh");
			break;
		}

	}

	public static void navigation(String naviagte, String url) {

		driver.navigate().to(url);
	}

	public static void alert(String alerttype) {

		driver.switchTo().alert().accept();

	}

	public static void alert(String alerttype, String command) {

		if (command == "accept") {

			driver.switchTo().alert().accept();

		} else {

			driver.switchTo().alert().dismiss();

		}

	}

	public static void alert(String alerttype, String command, String textinput) {

		driver.switchTo().alert().sendKeys(textinput);

		if (command == "accept") {

			driver.switchTo().alert().accept();

		} else {

			driver.switchTo().alert().dismiss();

		}

	}

	public static void getattributevalue(WebElement attribute, String attributediscription) {

		String attributevalue = attribute.getAttribute("value");
		System.out.println(attributediscription + ":" + attributevalue);

	}

	public void action(WebElement element) {
		Actions act = new Actions(driver);

		act.moveToElement(element).build().perform();
	}

	public void action(WebElement source, WebElement destination) {
		Actions act = new Actions(driver);
		act.dragAndDrop(source, destination).build().perform();
	}

	public static void screencapture(String name) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File sourcefile = ts.getScreenshotAs(OutputType.FILE);
		File targetfile = new File("D:\\Deepak\\output\\baseclass\\" + name + ".png");
		FileUtils.copyFile(sourcefile, targetfile);
	}

	public void selectbyindex(WebElement element, int index) {

		Select s = new Select(element);
		s.selectByIndex(index);

	}

	public void selectbyvalue(WebElement element, String value) {

		Select s = new Select(element);
		s.selectByValue(value);

	}

	public static void windowshandling(int windownumber) {

		Set<String> windows = driver.getWindowHandles();

		List<String> l = new LinkedList();
		l.addAll(windows);

		String tab = l.get(windownumber - 1);
		driver.switchTo().window(tab);

	}

	public static void frame(WebElement element) {

		driver.switchTo().frame(element);

	}

	public static void scroll(WebElement element, String action) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		if (action == "up") {

			js.executeScript("arguments[0].scrollIntoView(true)", element);

		} else {
			js.executeScript("arguments[0].scrollIntoView(false)", element);

		}
	}

	public static void sendkeysbyJS(WebElement element, String input) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("argument[0].setAttribute('value','" + input + "')", element);

	}

	public static void getvaluebyJS(WebElement element, String description) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		Object executeScript = js.executeScript("return argument[0].getAttribute('value')", element);

		System.out.println(description + ":" + executeScript);

	}

	public String excelread(String filename, String sheetname, int rownum, int cellnum) throws IOException {

		File filepath = new File("D:\\Deepak\\excel\\" + filename + ".xlsx");

		FileInputStream inputfile = new FileInputStream(filepath);

		Workbook book = new XSSFWorkbook(inputfile);

		Sheet sheet = book.getSheet(sheetname);

		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		CellType cellType = cell.getCellType();
		String value = null;

		switch (cellType) {
		case NUMERIC:

			if (DateUtil.isCellInternalDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				SimpleDateFormat dateformat = new SimpleDateFormat("dd/MMM/yyyy");
				value = dateformat.format(date);

			} else {
				double numericCellValue = cell.getNumericCellValue();
				BigDecimal valueOf = BigDecimal.valueOf(numericCellValue);
				value = valueOf.toString();

			}

			break;
		case STRING:

			value = cell.getStringCellValue();

			break;

		default:
			System.out.print("None");
			break;
		}
		return value;

	}

	public void writecell(String filename, String sheetname, int rownum, int cellnum, String input) throws IOException {

		File filepath = new File("D:\\Deepak\\excel\\" + filename + ".xlsx");
		Workbook book = new XSSFWorkbook();

		Sheet createSheet = book.createSheet(sheetname);

		Row createRow = createSheet.createRow(rownum);

		Cell createCell = createRow.createCell(cellnum);
		createCell.setCellValue(input);
		FileOutputStream outputstream = new FileOutputStream(filepath);
		book.write(outputstream);
	}

	public static void robot(char character) throws AWTException {
		Robot r = new Robot();
		switch (character) {

		case 'a':
			r.keyPress(KeyEvent.VK_A);
			r.keyRelease(KeyEvent.VK_A);
			break;
		case 'b':
			r.keyPress(KeyEvent.VK_B);
			r.keyRelease(KeyEvent.VK_B);
			break;
		case 'c':
			r.keyPress(KeyEvent.VK_C);
			r.keyRelease(KeyEvent.VK_C);
			break;
		case 'd':
			r.keyPress(KeyEvent.VK_D);
			r.keyRelease(KeyEvent.VK_D);
			break;
		case 'e':
			r.keyPress(KeyEvent.VK_E);
			r.keyRelease(KeyEvent.VK_E);
			break;
		case 'f':
			r.keyPress(KeyEvent.VK_F);
			r.keyRelease(KeyEvent.VK_F);
			break;
		case 'g':
			r.keyPress(KeyEvent.VK_G);
			r.keyRelease(KeyEvent.VK_G);
			break;
		case 'h':
			r.keyPress(KeyEvent.VK_H);
			r.keyRelease(KeyEvent.VK_H);
			break;
		case 'i':
			r.keyPress(KeyEvent.VK_I);
			r.keyRelease(KeyEvent.VK_I);
			break;
		case 'j':
			r.keyPress(KeyEvent.VK_J);
			r.keyRelease(KeyEvent.VK_J);
			break;
		case 'k':
			r.keyPress(KeyEvent.VK_K);
			r.keyRelease(KeyEvent.VK_K);
			break;
		case 'l':
			r.keyPress(KeyEvent.VK_L);
			r.keyRelease(KeyEvent.VK_L);
			break;
		case 'm':
			r.keyPress(KeyEvent.VK_M);
			r.keyRelease(KeyEvent.VK_M);
			break;
		case 'n':
			r.keyPress(KeyEvent.VK_N);
			r.keyRelease(KeyEvent.VK_N);
			break;
		case 'o':
			r.keyPress(KeyEvent.VK_O);
			r.keyRelease(KeyEvent.VK_O);
			break;
		case 'p':
			r.keyPress(KeyEvent.VK_P);
			r.keyRelease(KeyEvent.VK_P);
			break;
		case 'q':
			r.keyPress(KeyEvent.VK_Q);
			r.keyRelease(KeyEvent.VK_Q);
			break;
		case 'r':
			r.keyPress(KeyEvent.VK_R);
			r.keyRelease(KeyEvent.VK_R);
			break;
		case 's':
			r.keyPress(KeyEvent.VK_S);
			r.keyRelease(KeyEvent.VK_S);
			break;
		case 't':
			r.keyPress(KeyEvent.VK_T);
			r.keyRelease(KeyEvent.VK_T);
			break;
		case 'u':
			r.keyPress(KeyEvent.VK_U);
			r.keyRelease(KeyEvent.VK_U);
			break;
		case 'v':
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_V);
			break;
		case 'w':
			r.keyPress(KeyEvent.VK_W);
			r.keyRelease(KeyEvent.VK_W);
			break;
		case 'x':
			r.keyPress(KeyEvent.VK_X);
			r.keyRelease(KeyEvent.VK_X);
			break;
		case 'y':
			r.keyPress(KeyEvent.VK_Y);
			r.keyRelease(KeyEvent.VK_Y);
			break;
		case 'z':
			r.keyPress(KeyEvent.VK_Z);
			r.keyRelease(KeyEvent.VK_Z);
			break;

		case 'A':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_A);
			r.keyRelease(KeyEvent.VK_A);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'B':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_B);
			r.keyRelease(KeyEvent.VK_B);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'C':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_C);
			r.keyRelease(KeyEvent.VK_C);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'D':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_D);
			r.keyRelease(KeyEvent.VK_D);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'E':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_E);
			r.keyRelease(KeyEvent.VK_E);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'F':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_F);
			r.keyRelease(KeyEvent.VK_F);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'G':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_G);
			r.keyRelease(KeyEvent.VK_G);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'H':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_H);
			r.keyRelease(KeyEvent.VK_H);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'I':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_I);
			r.keyRelease(KeyEvent.VK_I);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'J':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_J);
			r.keyRelease(KeyEvent.VK_J);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'K':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_K);
			r.keyRelease(KeyEvent.VK_K);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'L':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_L);
			r.keyRelease(KeyEvent.VK_L);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'M':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_M);
			r.keyRelease(KeyEvent.VK_M);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'N':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_N);
			r.keyRelease(KeyEvent.VK_N);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'O':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_O);
			r.keyRelease(KeyEvent.VK_O);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'P':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_P);
			r.keyRelease(KeyEvent.VK_P);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'Q':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_Q);
			r.keyRelease(KeyEvent.VK_Q);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'R':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_R);
			r.keyRelease(KeyEvent.VK_R);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'S':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_S);
			r.keyRelease(KeyEvent.VK_S);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'T':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_T);
			r.keyRelease(KeyEvent.VK_T);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'U':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_U);
			r.keyRelease(KeyEvent.VK_U);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'V':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'W':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_W);
			r.keyRelease(KeyEvent.VK_W);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'X':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_X);
			r.keyRelease(KeyEvent.VK_X);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'Y':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_Y);
			r.keyRelease(KeyEvent.VK_Y);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'Z':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_Z);
			r.keyRelease(KeyEvent.VK_Z);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '0':
			r.keyPress(KeyEvent.VK_0);
			r.keyRelease(KeyEvent.VK_0);
			break;
		case '1':
			r.keyPress(KeyEvent.VK_1);
			r.keyRelease(KeyEvent.VK_1);
			break;
		case '2':
			r.keyPress(KeyEvent.VK_2);
			r.keyRelease(KeyEvent.VK_2);
			break;
		case '3':
			r.keyPress(KeyEvent.VK_3);
			r.keyRelease(KeyEvent.VK_3);
			break;
		case '4':
			r.keyPress(KeyEvent.VK_4);
			r.keyRelease(KeyEvent.VK_4);
			break;
		case '5':
			r.keyPress(KeyEvent.VK_5);
			r.keyRelease(KeyEvent.VK_5);
			break;
		case '6':
			r.keyPress(KeyEvent.VK_6);
			r.keyRelease(KeyEvent.VK_6);
			break;
		case '7':
			r.keyPress(KeyEvent.VK_7);
			r.keyRelease(KeyEvent.VK_7);
			break;
		case '8':
			r.keyPress(KeyEvent.VK_8);
			r.keyRelease(KeyEvent.VK_8);
			break;
		case '9':
			r.keyPress(KeyEvent.VK_9);
			r.keyRelease(KeyEvent.VK_9);
			break;
		case ',':
			r.keyPress(KeyEvent.VK_COMMA);
			r.keyRelease(KeyEvent.VK_COMMA);
			break;
		case '<':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_COMMA);
			r.keyRelease(KeyEvent.VK_COMMA);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '.':
			r.keyPress(KeyEvent.VK_PERIOD);
			r.keyRelease(KeyEvent.VK_PERIOD);
			break;
		case '>':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_PERIOD);
			r.keyRelease(KeyEvent.VK_PERIOD);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '/':
			r.keyPress(KeyEvent.VK_SLASH);
			r.keyRelease(KeyEvent.VK_SLASH);
			break;
		case '?':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_SLASH);
			r.keyRelease(KeyEvent.VK_SLASH);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case ';':
			r.keyPress(KeyEvent.VK_SEMICOLON);
			r.keyRelease(KeyEvent.VK_SEMICOLON);
			break;
		case ':':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_SEMICOLON);
			r.keyRelease(KeyEvent.VK_SEMICOLON);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '!':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_1);
			r.keyRelease(KeyEvent.VK_1);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '@':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_2);
			r.keyRelease(KeyEvent.VK_2);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '#':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_3);
			r.keyRelease(KeyEvent.VK_3);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '$':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_4);
			r.keyRelease(KeyEvent.VK_4);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '%':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_5);
			r.keyRelease(KeyEvent.VK_5);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '^':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_6);
			r.keyRelease(KeyEvent.VK_6);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '&':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_7);
			r.keyRelease(KeyEvent.VK_7);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '*':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_8);
			r.keyRelease(KeyEvent.VK_8);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '(':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_9);
			r.keyRelease(KeyEvent.VK_9);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case ')':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_9);
			r.keyRelease(KeyEvent.VK_9);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '-':
			r.keyPress(KeyEvent.VK_MINUS);
			r.keyRelease(KeyEvent.VK_MINUS);
			break;
		case '_':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_MINUS);
			r.keyRelease(KeyEvent.VK_MINUS);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '=':
			r.keyPress(KeyEvent.VK_EQUALS);
			r.keyRelease(KeyEvent.VK_EQUALS);
			break;
		case '+':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_EQUALS);
			r.keyRelease(KeyEvent.VK_EQUALS);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '[':
			r.keyPress(KeyEvent.VK_OPEN_BRACKET);
			r.keyRelease(KeyEvent.VK_OPEN_BRACKET);
			break;
		case '{':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_OPEN_BRACKET);
			r.keyRelease(KeyEvent.VK_OPEN_BRACKET);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case ']':
			r.keyPress(KeyEvent.VK_CLOSE_BRACKET);
			r.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
			break;
		case '}':
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_CLOSE_BRACKET);
			r.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		}

	}

	public static void robot(String keys) throws AWTException {
		Robot r = new Robot();

		switch (keys) {
		case "enter":
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			break;
		case "ctrl":
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_CONTROL);
			break;
		case "tab":
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			break;
		case "alt":
			r.keyPress(KeyEvent.VK_ALT);
			r.keyRelease(KeyEvent.VK_ALT);
			break;

		}

	}
}
