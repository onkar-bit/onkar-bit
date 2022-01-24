
package NewTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ReadfromFile {
		
		WebDriver driver;
		
		public String readdata(String key) throws IOException {
			Properties properties = new Properties();
			FileInputStream fs = new FileInputStream("C:\\workplace\\AutomationTestingMaven\\data.properties");
			properties.load(fs);
			String text = properties.getProperty(key);
			return text;
		}
		
		@BeforeMethod
		public void launch() {
			System.setProperty("webdriver.chrome.driver", "C:\\workplace\\AutomationTestingMaven\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		
		@Test
		public void yahoo() throws IOException {
			driver.get(readdata("yahoo"));
			driver.findElement(By.xpath("//div[text()='Sign In']")).click();
			driver.findElement(By.xpath("//a[@id='createacc']")).click();
			driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(readdata("fname"));
			driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(readdata("lname"));
			driver.findElement(By.xpath("//input[@name='yid']")).sendKeys(readdata("mail"));
			driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(readdata("mobile"));
			Reporter.log(driver.getTitle(), true);
			Reporter.log(driver.getCurrentUrl(), true);
			}

	}

