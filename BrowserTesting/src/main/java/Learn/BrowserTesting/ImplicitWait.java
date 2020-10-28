package Learn.BrowserTesting;

import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ImplicitWait {

	public static void main(String[] args) {
		
		
			WebDriver driver;
			String baseUrl;
			WebElement element;
			System.setProperty("webdriver.chrome.driver",  "C:/WebDriver/bin/chromedriver.exe");
			driver = new ChromeDriver();
				try {
					
					baseUrl = "http://www.google.com";
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.get(baseUrl);
					element = driver.findElement(By.xpath("//input[@name=q]"));
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
