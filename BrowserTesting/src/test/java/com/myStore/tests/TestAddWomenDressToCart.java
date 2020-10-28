package com.myStore.tests;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.myStore.webPages.Home;
import com.myStore.webPages.SummerDresses;
import com.myStore.webPages.Women;

public class TestAddWomenDressToCart {
	private static WebDriver driver;
	
	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.chrome.driver",  "C:/WebDriver/bin/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@Test
	public void testToAddWowmenDressToCartViaHomePage() throws Exception{
		try {
			Home home = new Home(driver);
			Assert.assertTrue(home.isPageOpened());
			home.clickOnWomenButton();
System.out.println(driver.getTitle());	        

			
			
			Women women = new Women(driver);
			women.clickOnSearchTxtBox();
			women.enterSearchDress();
			women.clickOnSearchTxtBox();
			women.selectFirstDress();
			women.addToCart();
			System.out.println("***Test Complete****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
		
		
	}
	
	@Test
	public void verifyHoverfunctionOnHome() {
		Home home = new Home(driver);
//			home.isPageOpened();
		home.hoverWomenAndClickSummerDresses();
		System.out.println(driver.getTitle());
		SummerDresses summerDresses = new SummerDresses(driver);
		summerDresses.clickOnWhiteColourCheckbox();
//		driver.close();
//		driver.quit();
	}
	
	
	

}
