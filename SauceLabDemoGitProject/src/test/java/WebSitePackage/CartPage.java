package WebSitePackage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	public CartPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (id  ="checkout")
	WebElement checkOutbtn;
	@FindBy(id = "continue-shopping")
	WebElement continueShoppingBtn;
	
	@FindBy (xpath = "//div[contains(@data-test,'inventory-item-price')]")
	List<WebElement> price;
	
	public Double findTotalPrice() {
		Double totalPrice=0.0;
		for(WebElement p:price) {
	totalPrice+=	Double.parseDouble(p.getText().substring(1));
		}
		return Math.round (totalPrice*100.0)/100.0;
	}
	
	public void clickCheckoutButton() {
		checkOutbtn.click();
	}
}
