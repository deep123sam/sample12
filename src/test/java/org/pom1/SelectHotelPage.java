package org.pom1;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SelectHotelPage extends BaseClass {
	
	public SelectHotelPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="radiobutton_0")
	private WebElement radiobutton;
	public WebElement getRadiobutton() {
		return radiobutton;
	}
	
	@FindBy(how=How.NAME,using="continue")
	private WebElement continuebutton;
	public WebElement getContinuebutton() {
		return continuebutton;
	}

}
