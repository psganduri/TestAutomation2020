package com.myStore.webPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SummerDresses {
	private WebDriver driver;
	private String PAGE_URL="http://automationpractice.com/index.php";
	
	
	
	@FindBy(xpath= "//input[@name='layered_id_attribute_group_8']")
	WebElement ckBoxWhite;
	

	public SummerDresses(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void clickOnWhiteColourCheckbox() {
		ckBoxWhite.click();
	}

}
