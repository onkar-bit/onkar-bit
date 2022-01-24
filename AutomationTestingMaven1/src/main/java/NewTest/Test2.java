package NewTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test2 {
	
	WebDriver driver;
	
	public String readdata(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream fs = new FileInputStream("C:\\workplace\\AutomationTestingMaven\\data.properties");
		properties.load(fs);
		String val = properties.getProperty(key);
		return val;
	}
	
	public void takescreen(WebDriver driver, String name) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File("C:\\workplace\\AutomationTestingMaven\\Screenshot\\"+name+".png");
		FileUtils.copyFile(SrcFile, DestFile);
		
	}
	
	@Parameters("browser")
	@BeforeTest
	public void launch(String browser) {
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
	public void frame() throws IOException {
		driver.get(readdata("frameurl"));
		driver.switchTo().frame("frame1");
		driver.findElement(By.xpath("//b[@id='topic']/../input")).sendKeys(readdata("topic"));
		
		driver.switchTo().frame("frame3");
		driver.findElement(By.xpath("//input[@id='a']")).click();
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("frame2");
		
		WebElement drop =driver.findElement(By.xpath("//select[@id='animals']"));
		Select obj = new Select(drop);
		obj.selectByValue(readdata("frame2val"));
		takescreen(driver, "frame");
	}
	
	@Test(priority = 1 )
	public void printlink() throws IOException {
		driver.get(readdata("yahoo"));
		List<WebElement> alllink = driver.findElements(By.tagName("a"));
		System.out.println("the count: "+alllink.size());
		
		for(int i=0;i<alllink.size();i++) {
			System.out.println("the all link: "+alllink.get(i).getText());
		}
	}
	
	@Test(priority = 2)
	public void form() throws IOException, InterruptedException {
		driver.get(readdata("formurl"));
		Thread.sleep(4000);
		//driver.findElement(By.xpath("//a[@id='cookieChoiceDismiss']")).click();
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(readdata("formname"));
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(readdata("formlname"));
		driver.findElement(By.xpath("//input[@id='sex-0']")).click();
		driver.findElement(By.xpath("//input[@id='exp-0']")).click();
		driver.findElement(By.xpath("//input[@id='profession-1']")).click();
		driver.findElement(By.xpath("//input[@id='tool-2']")).click();
		takescreen(driver, "filledform");
	}
	
	@Test(priority = 4)
	public void demoaction() throws IOException, InterruptedException {
		driver.get(readdata("demourl"));
		WebElement rclick = driver.findElement(By.xpath("//span[text()='right click me']"));
		Actions action = new Actions(driver);
		action.contextClick(rclick).build().perform();
		
		WebElement dclick = driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
		Actions a1 = new Actions(driver);
		a1.doubleClick(dclick).build().perform();
	}
	
	@Test(priority = 3)
	public void mouse() throws InterruptedException, IOException {	
		driver.get(readdata("mouseover"));
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
		WebElement mouse =driver.findElement(By.xpath("//div[text()='More']"));
		Actions move = new Actions(driver);
		move.moveToElement(mouse).build().perform();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//div[text()='24x7 Customer Care']")).click();
	}
	
	@AfterTest
	public void close() {
		driver.close();
		}

}
