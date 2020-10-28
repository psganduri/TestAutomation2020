package com.myStore.tests;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.myStore.webPages.Home;
import com.myStore.webPages.SummerDresses;
import com.myStore.webPages.Women;

public class TestActions {

	private static WebDriver driver;
	
	Home home = new Home(driver);
	Women women = new Women(driver);
	SummerDresses sumDresses = new SummerDresses(driver);

	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
	}
	
	
	@Test
	public void someActions() {
//		System.out.println("Test Complete");
		home.hoverWomenAndClickTops();
	}
	@AfterClass
	public static void tearDown() {
		driver.close();
		driver.quit();
	}
	public TestActions() {
		// TODO Auto-generated constructor stub
		
		}
	}


