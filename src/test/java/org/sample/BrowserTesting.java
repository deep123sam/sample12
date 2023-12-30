package org.sample;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserTesting {
	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();

		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys("iphone 15 pro max", Keys.ENTER);

		for (int i = 1; i < 10; i++) {

			WebElement iphone = driver
					.findElement(By.xpath("(//span[@class=\"a-size-medium a-color-base a-text-normal\"])[" + i + "]"));
			String name = iphone.getText();

			WebElement price = driver.findElement(By.xpath("(//span[@class=\"a-price-whole\"])[" + i + "]"));
			String iprice = price.getText();

			if (name.contains("iPhone 15 Pro Max")) {

				System.out.println(name + "---" + iprice);

			}
		}

	}

}
