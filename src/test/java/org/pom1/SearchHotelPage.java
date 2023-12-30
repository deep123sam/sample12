package org.pom1;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchHotelPage extends BaseClass {
	
	public SearchHotelPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="location")
	private WebElement location;
	public WebElement getLocation() {
		return location;
	}
	
	@FindBy(id="hotels")
	private WebElement hotels;
	public WebElement getHotels() {
		return hotels;
	}
	
	@FindBy(id="room_type")
	private WebElement roomtype;
	public WebElement getRoomtype() {
		return roomtype;
	}
	
	@FindBy(id="room_nos")
	private WebElement roomnos;
	public WebElement getRoomnos() {
		return roomnos;
	}
	
	@FindBy(id="datepick_in")
	private WebElement checkin;
	public WebElement getCheckin() {
		return checkin;
	}
	
	@FindBy(id="datepick_out")
	private WebElement checkout ;
	public WebElement getCheckout() {
		return checkout;
	}
	
	@FindBy(id="adult_room")
	private WebElement adultroom;
	public WebElement getAdultroom() {
		return adultroom;
	}
	
	@FindBy(id="child_room")
	private WebElement childroom ;
	public WebElement getChildroom() {
		return childroom;
	}
	
	@FindBy(id="Submit")
	private WebElement search;
	public WebElement getSearch() {
		return search;
	}
}
