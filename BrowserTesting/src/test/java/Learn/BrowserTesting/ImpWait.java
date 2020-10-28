package Learn.BrowserTesting;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ImpWait {
	private static WebDriver driver;
	private String baseUrl;
	private WebElement element;
	
	
	@BeforeClass
	public static  void setUp() {
		System.setProperty("webdriver.chrome.driver",  "C:/WebDriver/bin/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	@Test
	public void unnamedTest() {
		
		try {
			baseUrl = "http://www.google.com";
			System.out.println(baseUrl);
			driver.get("http://www.google.com");
			driver.get(baseUrl);
			element = driver.findElement(By.xpath("//input[@title=Search]"));
			element.sendKeys("Selenium WebDriver Interview questions");
			element.sendKeys(Keys.RETURN);
			driver.findElement(By.className("di3YZe"));
			WebElement element1 = driver.findElement(By.className("di3YZe"));
			System.out.println("element1.getSize()");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		driver.quit();
		
	}
	
	
	

}
