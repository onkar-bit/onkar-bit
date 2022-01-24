package NewTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test1 {
	
	WebDriver driver;
	
	public String readdata(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream fs = new FileInputStream("C:\\workplace\\AutomationTestingMaven\\data.properties");
		properties.load(fs);
		String text =properties.getProperty(key);
		return text;
	}
	
	public void screenshot(WebDriver driver, String name) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File("C:\\workplace\\AutomationTestingMaven\\Screenshot\\"+name+".png");
		FileUtils.copyFile(SrcFile, DestFile);
	}
	
	@Parameters("browser")
	@BeforeTest
	public void launchbrowser(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\workplace\\AutomationTestingMaven\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\workplace\\AutomationTestingMaven\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
	}
	
	
	@Test(priority = 0)
	public void scroll() throws IOException {
		driver.get(readdata("amazonurl"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,900)");
		js.executeScript("window.scrollBy(0,-900)");
		
	}
	
	@Test(priority = 1)
	public void window() throws IOException, InterruptedException {
		driver.get(readdata("windowurl"));
		driver.findElement(By.xpath("//a[text()=' Click this link to start new Tab '][2]")).click();
		
		String parentwindow = driver.getWindowHandle();
		
		ArrayList<String> allwindow = new ArrayList<String>(driver.getWindowHandles());
		
		driver.switchTo().window(allwindow.get(1));
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(readdata("fname"));
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(readdata("lname"));
		screenshot(driver, "facebook");
		Reporter.log("the title 2: "+driver.getTitle(), true);
		Reporter.log("the url 2: "+driver.getCurrentUrl(), true);
		
		driver.switchTo().window(allwindow.get(0));
		Reporter.log("The title 1: " +driver.getTitle(), true);
		Reporter.log("The url 1: " +driver.getCurrentUrl(), true);
		
	}
	
	@Test(priority = 2)
	public void dropdown() throws IOException {
		driver.get(readdata("dropurl"));
		WebElement drop = driver.findElement(By.xpath("//div[@class='information closable']/../p/select"));
		Select obj = new Select(drop);
		obj.selectByValue(readdata("dropvalue"));
		screenshot(driver,"dropdownVal");
	}
	
	@Test(priority = 3)
	public void alert() throws IOException, InterruptedException {
		driver.get(readdata("alerturl"));
		driver.findElement(By.xpath("//button[@id='alertButton']")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		driver.findElement(By.xpath("//button[@id='timerAlertButton']")).click();
		Thread.sleep(9000);
		Alert a1 = driver.switchTo().alert();
		a1.accept();
		
		driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
		Alert a2 = driver.switchTo().alert();
		a2.accept();
		String text = driver.findElement(By.xpath("//span[@id='confirmResult']")).getText();
		Reporter.log(text);
		
		driver.findElement(By.xpath("//button[@id='promtButton']")).click();
		Alert a3 = driver.switchTo().alert();
		a3.sendKeys("abc123");
		a3.accept();
		String t1 = driver.findElement(By.xpath("//span[@id='promptResult']")).getText();
		Reporter.log(t1);
		
			
	}
	
	@Test(priority = 4)
	public void Verifytitle() throws IOException {
		driver.get(readdata("googleUrl"));
		String actual = driver.getTitle();
		String url = driver.getCurrentUrl();
		System.out.println("the source: "+driver.getPageSource());
		Reporter.log(actual, true);
		Reporter.log(url, true);
		String expected = "Google";
		Assert.assertEquals(actual, expected);
		
	}
	
	@AfterTest
	public void close() {
		driver.close();
	}

}
