package org.pom1;

import java.io.IOException;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;

public class Adactintesting extends BaseClass{

	public static void main(String[] args) throws IOException {

		BaseClass b = new BaseClass();

		getdriver("Chrome");
		geturl(b.excelread("stu_data", "Sheet3", 3, 2));
		
		Loginpage page=new Loginpage();
		
		WebElement username = page.getUsername();
		b.sendkeys(username, b.excelread("stu_data", "Sheet3", 3, 0));
		
		WebElement password = page.getPassword();
		b.sendkeys(password, b.excelread("stu_data", "Sheet3", 3, 1));
		
		WebElement login = page.getLogin();
		login.click();
		
		SearchHotelPage select1=new SearchHotelPage();
		
		WebElement location = select1.getLocation();
		b.selectbyvalue(location,"Melbourne");

		WebElement hotels = select1.getHotels();
		b.selectbyvalue(hotels,"Hotel Sunshine");
		
		WebElement roomtype = select1.getRoomtype();
		b.selectbyvalue(roomtype,"Double");
		
		WebElement roomnos = select1.getRoomnos();
		b.selectbyvalue(roomnos,"2");
		
		WebElement checkin = select1.getCheckin();
		checkin.clear();
		b.sendkeys(checkin, "18/12/2023");
		
		WebElement checkout = select1.getCheckout();
		checkout.clear();
		b.sendkeys(checkout, "20/12/2023");
		
		WebElement adultroom = select1.getAdultroom();
		b.selectbyvalue(adultroom,"4");
		
		WebElement childroom = select1.getChildroom();
		b.selectbyvalue(childroom,"2");
		
		WebElement search = select1.getSearch();		
		search.click();
		
		SelectHotelPage select2=new SelectHotelPage();
		
		WebElement radiobutton = select2.getRadiobutton();
		radiobutton.click();
		
		WebElement continuebutton = select2.getContinuebutton();
		continuebutton.click();
		
		BookHotelPage select3=new BookHotelPage();
		
		WebElement firstname = select3.getFirstname();
		b.sendkeys(firstname, "Deepak");
		
		WebElement lastname = select3.getLastname();
		b.sendkeys(lastname, "Kumar");
		
		WebElement address = select3.getAddress();
		b.sendkeys(address, "253/B2,Savadi Street\nAnbu Nagar-3,\nNamakkal-637001.");
		
		WebElement cardnum = select3.getCardnum();
		b.sendkeys(cardnum, "4876454500043521");
		
		WebElement cardtype = select3.getCardtype();
		b.selectbyvalue(cardtype, "MAST");
		
		WebElement cardmonth = select3.getCardmonth();
		b.selectbyvalue(cardmonth, "8");
		
		WebElement cardyear = select3.getCardyear();
		b.sendkeys(cardyear, "2028");
		
		WebElement cardcvv = select3.getCardcvv();
		b.sendkeys(cardcvv, "989");
		
		WebElement booknow = select3.getBooknow();
		booknow.click();
	}

}
