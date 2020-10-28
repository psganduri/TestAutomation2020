package Learn.BrowserTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstSeleniumTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",  "C:/WebDriver/bin/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
//		Open the website
		driver.get("http://automationpractice.com/index.php");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		By btnWomen = (By.xpath("//li/a[@title='Women'][contains(text(),'Women')]"));
		By txtSearch = (By.xpath("//input[@name='search_query']"));
		By btnAddtoCart = (By.xpath("//li/a[@title='Women'][contains(text(),'Women')]"));
		By imgFirst = (By.xpath("//div[@class='left-block']/descendant::img[@title='Printed Summer Dress'][1]"));
//		By btnTshirts = (By.xpath("//li/a[@title='T-shirts'][contains(text(),'T-shirts')][0]"));
		
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(btnWomen));
//		2. Click "Women" button //li/a[@title='Women'][contains(text(),'Women')]
			driver.findElement(By.xpath("//li/a[@title='Women'][contains(text(),'Women')]")).click();
//		wait.until(ExpectedConditions.presenceOfElementLocated(btnTshirts));
//		driver.findElement(By.xpath("//li/a[@title='T-shirts'][contains(text(),'T-shirts')][0]")).click();
			System.out.println("step 2 clicking on women done");
//		3. Search "Dress" in search dialog
			wait.until(ExpectedConditions.presenceOfElementLocated(txtSearch));
			driver.findElement(By.xpath("//input[@name='search_query']")).sendKeys("Dress");
			System.out.println("step 3 send keys to search done");
// 		send keys "Dress" and click on //button[@type='submit'][@name='submit_search']
			driver.findElement(By.xpath("//button[@type='submit'][@name='submit_search']")).click();
			System.out.println("Clicked on Search Done");
//		4. Click the first picture for dress $x("//div[@class='left-block']/descendant::img[@title='Printed Summer Dress'][1]")
			wait.until(ExpectedConditions.presenceOfElementLocated(imgFirst));
			driver.findElement(By.xpath("//div[@class='left-block']/descendant::img[@title='Printed Summer Dress'][1]")).click();
			System.out.println("Step 4 Clicked on first Picture in search Results");
//		5. Click "Add to chart" $x("//span[contains(text(),'Add to cart')]")
			wait.until(ExpectedConditions.presenceOfElementLocated(btnAddtoCart));
			driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]")).click();
			System.out.println("Step 5 Add to cart Test complete");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();

		
	}

}
