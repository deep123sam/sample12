package org.sample;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowSwitchAmazon {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();

		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys("samsung s23 ultra", Keys.ENTER);

		WebElement samsung = driver.findElement(
				By.xpath("(//span[text()='Samsung Galaxy S23 Ultra 5G (Phantom Black, 12GB, 256GB Storage)'])[1]"));
		samsung.click();

		Thread.sleep(2000);

		String parentWindow = driver.getWindowHandle();

		Set<String> childwindow = driver.getWindowHandles();

		for (String child1 : childwindow) {
			if (!parentWindow.equals(child1)) {

				driver.switchTo().window(child1);
			}

		}

		WebElement addtocart = driver.findElement(By.id("add-to-cart-button"));
		addtocart.click();

		Thread.sleep(5000);

		driver.switchTo().window(parentWindow);

		WebElement search1 = driver.findElement(By.id("twotabsearchtextbox"));
		search1.clear();
		search1.sendKeys("oneplus 11");
		search1.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		WebElement oneplus = driver
				.findElement(By.xpath("//span[text()='OnePlus 11 5G (Eternal Green, 8GB RAM, 128GB Storage)'] "));
		oneplus.click();

		Thread.sleep(2000);

		Set<String> windowHandles = driver.getWindowHandles();

		List<String> l = new LinkedList();
		l.addAll(windowHandles);

		String oneplustab = l.get(2);
		driver.switchTo().window(oneplustab);

		Thread.sleep(2000);

		addtocart = driver.findElement(By.id("add-to-cart-button"));
		addtocart.click();

		Thread.sleep(5000);

		driver.switchTo().window(parentWindow);

		WebElement search2 = driver.findElement(By.id("twotabsearchtextbox"));
		search2.clear();
		search2.sendKeys("iqoo neo 7 pro");
		search2.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		WebElement iqoo = driver.findElement(
				By.xpath("//span[contains(text(),'iQOO Neo 7 Pro 5G (Fearless Flame, 12GB RAM, 256GB Storage)')]"));
		iqoo.click();

		Thread.sleep(2000);

		Set<String> windowHandles1 = driver.getWindowHandles();

		List<String> l1 = new LinkedList();
		l1.addAll(windowHandles1);

		String iqootab = l1.get(3);
		driver.switchTo().window(iqootab);

		Thread.sleep(2000);

		addtocart = driver.findElement(By.id("add-to-cart-button"));
		addtocart.click();

		Thread.sleep(5000);

		driver.switchTo().window(parentWindow);

		WebElement search3 = driver.findElement(By.id("twotabsearchtextbox"));
		search3.clear();
		search3.sendKeys("iphone 14 pro");
		search3.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		WebElement iphone = driver.findElement(By.xpath("//span[contains(text(), 'iPhone 14 Pro (256 GB) - Gold')]"));
		iphone.click();

		Thread.sleep(2000);

		Set<String> windowHandles2 = driver.getWindowHandles();

		Thread.sleep(2000);

		List<String> l2 = new LinkedList();
		l2.addAll(windowHandles2);

		Thread.sleep(2000);

		String iphonetab = l2.get(4);
		driver.switchTo().window(iphonetab);

		Thread.sleep(2000);

		addtocart = driver.findElement(By.id("add-to-cart-button"));
		addtocart.click();

		Thread.sleep(5000);

		driver.switchTo().window(parentWindow);

		WebElement search4 = driver.findElement(By.id("twotabsearchtextbox"));
		search4.clear();
		search4.sendKeys("redmi 12");
		search4.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		WebElement redmi = driver
				.findElement(By.xpath("//span[contains(text(),'Redmi 12 5G Moonstone Silver 4GB RAM 128GB ROM')] "));
		redmi.click();

		Thread.sleep(2000);

		Set<String> windowHandles3 = driver.getWindowHandles();

		List<String> l3 = new LinkedList();
		l3.addAll(windowHandles3);

		Thread.sleep(2000);

		String redmitab = l3.get(5);
		driver.switchTo().window(redmitab);

		Thread.sleep(2000);

		addtocart = driver.findElement(By.id("add-to-cart-button"));
		addtocart.click();

		Thread.sleep(5000);

	}

}
