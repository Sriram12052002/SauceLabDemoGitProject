package WebSitePackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class FinishPage {
	public FinishPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (tagName  ="h2")
	WebElement thankYouMsg;
	
	@FindBy (id ="back-to-products")
	WebElement backToHomeBtn;
	
	
	public void verifyThankYouMsg() {
		String expectedMsg = "Thank you for your order!";
		String actualMsg = thankYouMsg.getText();
		Assert.assertEquals(expectedMsg, actualMsg);
		
		
	}
	
	public void clickOnBackToHomeBtn(){
	backToHomeBtn.click();
		
	}
}
