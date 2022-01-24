package Launchgoogle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Parametermulti {
	
	WebDriver driver;
	
	@Parameters("browser")
	@BeforeTest
	public void browser(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\workplace\\chromedriver_win32\\chromedriver.exe");
			driver= new ChromeDriver();
			driver.manage().window().maximize();
			
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\workplace\\geckodriver-v0.30.0-win64\\geckodriver.exe");
			driver= new FirefoxDriver();
			driver.manage().window().maximize();
		}
		
		else if(browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", "C:\\workplace\\IEDriverServer_Win32_4.0.0\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		}
		
		
	}
	
	@AfterTest
	public void close() {
		driver.close();
	}
	
	public void openurl(String url) {
		driver.get(url);
	}
	
	public String get() {
		return driver.getTitle();
	}
	
	@Test
	public void amazon() {
		openurl("https://www.amazon.com");
		System.out.println("the title: "+get());
	}
	
	@Test
	public void google() {
		openurl("https://www.google.com");
		System.out.println("the title: "+get());
	}
	
	
	

}
