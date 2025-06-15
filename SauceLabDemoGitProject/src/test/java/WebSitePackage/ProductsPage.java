package WebSitePackage;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ProductsPage {
	public  WebDriver driver;
	 Select select;
	public ProductsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (tagName="select")
	WebElement sortByDropDown;
	@FindBy (xpath="(//div[contains(@data-test,'inventory-item-name')])[1]")
	WebElement firstElementName;
	@FindBy (xpath="(//div[contains(@data-test,'inventory-item-name')])[last()]")
	WebElement lastElementName;
	@FindBy(xpath = "(//div[contains(@data-test,'inventory-item-price')])[1]")
	WebElement firstPrice;
	@FindBy(xpath = "(//div[contains(@data-test,'inventory-item-price')])[last()]")
	WebElement lastPrice;	
	@FindBy(xpath = "(//button[contains(@id,'add-to-cart-sauce-labs')])[1]")
	WebElement firstAddToCart;	
	@FindBy(xpath = "(//button[contains(@id,'add-to-cart-sauce-labs')])[last()]")
	WebElement lastAddToCart;
	@FindBy(xpath = "(//button[contains(@id,'remove')])[1]")
	WebElement firstRemove;	
	@FindBy(xpath = "(//button[contains(@id,'remove')])[last()]")
	WebElement lastRemove;
	@FindBy(xpath="//span[@data-test='shopping-cart-badge']")
	WebElement cartCount;
	@FindBy (xpath = "//a[@data-test='shopping-cart-link']")
	WebElement trolly;
			
	
	
	String firstElementNameText;
	String lastElementNameText ;
	double firstPriceText;
	double lastPriceText;
	
	
	public  void sortDropDownInitialization() {
		select = new Select(sortByDropDown);
		
		
	}
	public void textAndPriceInitialization() {
		firstElementNameText = firstElementName.getText();
		 lastElementNameText =lastElementName.getText();
	 firstPriceText =Double.parseDouble( firstPrice.getText().substring(1));
		 lastPriceText =Double.parseDouble( lastPrice.getText().substring(1));
	}
	
	public void sortDropDownAtoZ() throws InterruptedException {
		sortDropDownInitialization();
		select.selectByIndex(0);
		Thread.sleep(2000);
		textAndPriceInitialization();
		Thread.sleep(1000);
		
		int num =firstElementNameText.compareToIgnoreCase(lastElementNameText);
		Assert.assertTrue(num<0);
		
		
	}
	
	public void sortDropDownZtoA() throws InterruptedException {
		sortDropDownInitialization();
		select.selectByIndex(1);
		Thread.sleep(2000);
		textAndPriceInitialization();
		Thread.sleep(1000);
		int num =firstElementNameText.compareToIgnoreCase(lastElementNameText);
		Assert.assertTrue(num>0);
	}
	public void sortDropDownLowToHigh() throws InterruptedException {
		sortDropDownInitialization();
		select.selectByValue("lohi");
		Thread.sleep(2000);
		textAndPriceInitialization();
		Thread.sleep(1000);
		Assert.assertTrue(firstPriceText<lastPriceText);
	}
	public void sortDropDownHighToLow() throws InterruptedException {
		sortDropDownInitialization();
		select.selectByVisibleText("Price (high to low)");
		Thread.sleep(2000);
		textAndPriceInitialization();
		Thread.sleep(1000);
		Assert.assertTrue(firstPriceText>lastPriceText);
	}
	
	public void clickOnFirstAddToCart() {
		firstAddToCart.click();
		String firstRemoveButtonText = firstRemove.getText();
		Assert.assertEquals(firstRemoveButtonText, "Remove");
		
	}
	public void clickOnLastAddToCart() {
		lastAddToCart.click();
		String lastRemoveButtonText = lastRemove.getText();
		Assert.assertEquals(lastRemoveButtonText, "Remove");
	}
	public WebElement returnCartBadge() {
		return cartCount;
	}
	public void clickTrolly() {
		trolly.click();
		
	}
}
