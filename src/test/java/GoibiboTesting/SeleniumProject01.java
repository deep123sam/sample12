package GoibiboTesting;

import org.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SeleniumProject01 extends BaseClass {

	public static void main(String[] args) {

		BaseClass b = new BaseClass();

		b.getdriver("Edge");
		b.geturl("https://www.goibibo.com/");
		WebElement xbutton = driver.findElement(By.xpath("//span[@class=\"logSprite icClose\"]"));
		xbutton.click();

	}

}
