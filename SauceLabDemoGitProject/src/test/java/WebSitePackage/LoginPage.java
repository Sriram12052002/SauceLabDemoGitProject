package WebSitePackage;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (id = "user-name")
	WebElement username;
	
	@FindBy (id = "password")
	WebElement password;
	
	@FindBy (id = "login-button")
	WebElement loginButton;
	
	@FindBy(xpath="//*[contains(text(),'Epic sadface: Username is required')]")
	WebElement requiredErrorMessage;
	
	@FindBy(xpath = "//*[contains(text(),'Epic sadface: Username and "
			+ "password do not match any user in this service')]")
	WebElement incorrectErrorMessage;
	
	
	public void enterUserName(String Uname) {
		username.clear();
		username.sendKeys(Uname);
	}
	
	public void enterPassword(String pword) {
		password.clear();
		password.sendKeys(pword);
	}
	
	public void clickOnLoginButton() {
		loginButton.click();;
	}
	
	public void verifyRequiredErrorMessage() {
		Assert.assertTrue(requiredErrorMessage.isDisplayed());
	}
	public void verifyIncorrectErrorMessage() {
		Assert.assertTrue(incorrectErrorMessage.isDisplayed());
	}
	
	public String randomStringGenerator(int length) {
		String characters = "ABCEFGHIJKLMNOPQRSTUVWYZabcdefghijklmnopqrstuvwxyz1234567890@#$&*.!";
		Random random = new Random();
		StringBuilder sb = new StringBuilder(length);
		for(int i =0; i<length;i++) {
	sb.append(characters.charAt(random.nextInt(characters.length())));}
		return sb.toString();
		
	}
}
