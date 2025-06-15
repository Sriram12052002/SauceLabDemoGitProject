package WebSitePackage;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {
	WebDriver driver;
	 public CheckOutPage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 @FindBy (id = "first-name")
	 WebElement firstName;
	 WebElement lastName;
	 WebElement zipcode;
	 @FindBy (id="continue")
	 WebElement continuebtn;
	 
	 public void fillCheckOutDetails(String fname,String lname, String zip) throws InterruptedException {
		 firstName.sendKeys(fname,Keys.TAB);
		 Thread.sleep(1000);
	lastName=	 driver.switchTo().activeElement();	
		lastName.sendKeys(lname,Keys.TAB);
		zipcode=	 driver.switchTo().activeElement();;
		Thread.sleep(1000);
		 zipcode.sendKeys(zip);
			
	 }
	 
	 public void clickOnContinueButton() {
		 continuebtn.click();
	 }
}
