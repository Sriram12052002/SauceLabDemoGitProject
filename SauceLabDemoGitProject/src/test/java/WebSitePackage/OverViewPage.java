package WebSitePackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OverViewPage {
	
	public OverViewPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		}
	
	@FindBy (xpath = "//div[contains(@data-test,'subtotal-label')]")
	WebElement subTotalElement;
	@FindBy (id="finish")
	WebElement finishbtn;
	
	public Double returnTotal() {
		String totaltext[] = subTotalElement.getText().split("\\$");
		
		
		return Math.round(Double.parseDouble( totaltext[totaltext.length-1])*100.0)/100.0;
	}
	
	public void clickOnFinishBtn() {
		finishbtn.click();
	}
}
