package NewTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Calender {

	WebDriver driver;
	
	@BeforeMethod
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "C:\\workplace\\AutomationTestingMaven\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		}
	
	@Test
	public void url() throws InterruptedException {
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		driver.findElement(By.xpath("//input[@name='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='txtPassword']")).sendKeys("admin123");
		driver.findElement(By.xpath("//input[@name='Submit']")).click();
		
		WebElement mouse= driver.findElement(By.xpath("//a[@id='menu__Performance']"));
		Actions a1 = new Actions(driver);
		a1.moveToElement(mouse).build().perform();
		
		WebElement m2 =driver.findElement(By.xpath("//a[@id='menu_performance_ManageReviews']"));
		Actions a2 = new Actions(driver);
		a2.moveToElement(m2).build().perform();
		
		driver.findElement(By.xpath("//a[@id='menu_performance_searchPerformancReview']")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@id='fromDate']")).click();
		
		WebElement month = driver.findElement(By.xpath("//select[@data-handler='selectMonth']"));
		Select obj = new Select(month);
		obj.selectByValue("2");
		
		WebElement year = driver.findElement(By.xpath("//select[@data-handler='selectYear']"));
		Select obj2 = new Select(year);
		obj2.selectByValue("1996");
		
		driver.findElement(By.xpath("//a[text()='30']")).click();
	}
}
