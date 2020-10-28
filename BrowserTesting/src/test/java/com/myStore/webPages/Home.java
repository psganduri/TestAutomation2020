package com.myStore.webPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.bytebuddy.implementation.bind.annotation.Argument;

public class Home {
	private WebDriver driver;
	
//	Page Url
	private static String PAGE_URL="http://automationpractice.com/index.php";
	
//	Locators
	@FindBy(xpath="//h1[contains(text(),'Automation Practice Website')]")
	WebElement heading;
	
	@FindBy (xpath="//a[@title='Women']")
	private WebElement btnWomen;

	@FindBy (xpath="//a[@title='Dresses'][contains(text(),'Dresses')][1]")
	private WebElement btnDresses;
	
	@FindBy (xpath="//a[@title='T-shirts'][contains(text(),'T-shirts')][1]")
	private WebElement btnTshirts;
	
	@FindBy (xpath="//a[@title='Summer Dresses'][contains(text(),'Summer Dresses')][1]")
	private WebElement lnkSummerDresses;
	
	@FindBy(xpath="//a[@class='homefeatured']")
	private WebElement btnPopular;
	
	@FindBy (xpath="//a[@class='blockbestsellers']")
	private WebElement btnBestSellers;
	
	@FindBy (xpath="//a[@title='Tops']")
	private WebElement btnTops;

	
	
//Constructor
	public Home(WebDriver driver) {
		this.driver=driver;
		driver.get(PAGE_URL);
//		Initialise Elements
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
		
		 
	}
	
	public void clickOnWomenButton() {
		btnWomen.click();
	}

	public void clickOnDressesButton() {
		btnDresses.click();
	}
	public void clickOnTshirtsButton() {
		btnTshirts.click();
	}
	public void clickOnPopularButton() {
//		btnPopular.click();
	}
	public void clickOnBestsellersButton() {
		btnBestSellers.click();
	}
	public boolean isPageOpened() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)driver;	

        js.executeScript("history.go(0);");Thread.sleep(500);
        System.out.println(js.executeScript("return document.title;").toString());Thread.sleep(500);
        
        //      js.executeScript("arguments[0].scrollIntoView(true);",heading );	
      js.executeScript("Window.scrollBy(300,5000)");Thread.sleep(500);
		     
      
		return heading.getText().toString().contains("Automation Practice Website");
		
	}
	
	public void hoverWomenAndClickSummerDresses() {
		Actions action = new Actions(driver);
		action.moveToElement(btnWomen).moveToElement(lnkSummerDresses).click().build().perform();
		
	}
	public void hoverWomenAndClickTops() {
		Actions action = new Actions(driver);
		action.moveToElement(btnWomen).moveToElement(btnTops).click().build().perform();
		
	}
	
}
