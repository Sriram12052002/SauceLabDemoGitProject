package DriverPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


public class DriverInstanceClass {

public  WebDriver driver;
	

	
	@BeforeTest 
	@Parameters({"Browser"})

public void DriverSetup(String Browser) {
	
	
	if(Browser.equalsIgnoreCase("edge")) {
		 EdgeOptions option = new EdgeOptions();
		 option.setBinary("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe")	 ;
		 option.addArguments("--headless");
		option.addArguments("--disable-extension");
		option.addArguments("--disable-cookies");
		option.addArguments("--incognito");
		
		driver= new EdgeDriver(option);
	}
	else {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--headless");
		option.addArguments("--disable-extension");
		option.addArguments("--disable-cookies");
		option.addArguments("--incognito");
		
		driver = new ChromeDriver(option);
	}
}

@AfterTest
public void tearDown() {
	driver.close();
	
}
}
