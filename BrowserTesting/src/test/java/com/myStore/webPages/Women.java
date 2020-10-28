package com.myStore.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Women {
	private WebDriver driver;
	
	@FindBy (xpath="//input[@name='search_query']")
	private WebElement txtSearchbox; 

	
	@FindBy (xpath="//button[@type='submit'][@name='submit_search']")
	private WebElement btnSearch; 
	
	@FindBy (xpath="//div[@class='left-block']/descendant::img[@title='Printed Summer Dress'][1]")
	private WebElement imgFirst; 

	@FindBy (xpath="//span[contains(text(),'Add to cart')]")
	private WebElement btnAddToCart; 

	
	public Women(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	public void clickOnSearchTxtBox() {
		txtSearchbox.click();
	}
	
	public void enterSearchDress() {
		txtSearchbox.sendKeys("Dresses");
		
	}
	
	public void searchDress() {
		btnSearch.click();
	}
	
	public void selectFirstDress() {
		imgFirst.click();
	}
	
	public void addToCart() {
		btnAddToCart.click();
	}

}
