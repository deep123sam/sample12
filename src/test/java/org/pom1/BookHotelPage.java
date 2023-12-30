package org.pom1;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookHotelPage extends BaseClass{
	
	public BookHotelPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="first_name")
	private WebElement firstname ;
	public WebElement getFirstname() {
		return firstname;
	}
	
	@FindBy(id="last_name")
	private WebElement lastname ;
	public WebElement getLastname() {
		return lastname;
	}
	
	@FindBy(id="address")
	private WebElement address;
	public WebElement getAddress() {
		return address;
	}
	
	@FindBy(id="cc_num")
	private WebElement cardnum;
	public WebElement getCardnum() {
		return cardnum;
	}
	
	@FindBy(id="cc_type")
	private WebElement cardtype ;
	public WebElement getCardtype() {
		return cardtype;
	}
	
	@FindBy(id="cc_exp_month")
	private WebElement cardmonth;
	public WebElement getCardmonth() {
		return cardmonth;
	}
	
	@FindBy(id="cc_exp_year")
	private WebElement cardyear ;
	public WebElement getCardyear() {
		return cardyear;
	}
	
	@FindBy(id="cc_cvv")
	private WebElement cardcvv ;
	public WebElement getCardcvv() {
		return cardcvv;
	}
	
	@FindBy(id="book_now")
	private WebElement booknow ;
	public WebElement getBooknow() {
		return booknow;
	}

}
