package basePackage;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.itextpdf.text.DocumentException;

import DriverPackage.DriverInstanceClass;
import WebSitePackage.CartPage;
import WebSitePackage.CheckOutPage;
import WebSitePackage.FinishPage;
import WebSitePackage.LoginPage;
import WebSitePackage.OverViewPage;
import WebSitePackage.ProductsPage;
import reportPackage.reportClass;

public class baseTestRunnerClass extends DriverInstanceClass {
	
	LoginPage  lp;
	ProductsPage pp;
	Properties prop;
	String currentURL;
	CartPage cp;
	double totalcheckOutPrice;
	CheckOutPage cop;
	OverViewPage op;
	FinishPage fp;
	reportClass rp;
	
@Test(priority=-1)
public void ObjectCreations() throws FileNotFoundException, IOException, DocumentException {
	lp = new LoginPage(driver);
	rp= new reportClass(driver);
	
	prop = new Properties();
	prop.load(new FileInputStream("./Utilities/information.properties"));
}
@Test(priority=0)
public void openUrl() throws IOException, DocumentException {
	String URL = prop.getProperty("URL");
	driver.get(URL);
	rp.generateReportsMethod("URL Loaded SS", "URL Loaded");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	rp.generateReportsMethod("Maximized Window SS", "Window Maximized");
}
@Test(priority=1)
public void verificationOfEmptyAndInvalidUsernameOrPassword() throws InterruptedException, IOException, DocumentException {
	Thread.sleep(4000);
    lp.clickOnLoginButton();
    lp.verifyRequiredErrorMessage();
    Thread.sleep(4000);
    rp.generateReportsMethod("Username required Error message SS", "Username required Error message displayed");
    String InvalidUsername = lp.randomStringGenerator(8);
	lp.enterUserName(InvalidUsername);
	String InvalidPassword = lp.randomStringGenerator(8);
	lp.enterPassword(InvalidPassword);
	Thread.sleep(2000);
	lp.clickOnLoginButton();
	lp.verifyIncorrectErrorMessage();
	rp.generateReportsMethod("Incorrect Error message SS", "Incorrect Error message displayed");
	Thread.sleep(4000);
}
@Test(priority=2)
public void loginFunctionalities() throws InterruptedException, IOException, DocumentException {
	String uname = prop.getProperty("UserName");
	String pword = prop.getProperty("Password");
	lp.enterUserName(uname);
	lp.enterPassword(pword);
	Thread.sleep(3000);
	rp.generateReportsMethod("Credentials Entered SS", "Credentials Entered displayed");
	lp.clickOnLoginButton();
	Thread.sleep(3000);
	 currentURL = driver.getCurrentUrl();
	String ProductsPageURL= prop.getProperty("ProductsPageURl");
	Assert.assertEquals(ProductsPageURL, currentURL);	
	rp.generateReportsMethod("Product page SS", "Product page displayed");
	
}

@Test(priority = 3)
public void sortByFunctionalityVerification() throws InterruptedException, IOException, DocumentException {
	pp = new ProductsPage(driver);
	pp.sortDropDownAtoZ();
	rp.generateReportsMethod("Sorted A to Z SS", "Product sorted A to Z displayed");
	Thread.sleep(3000);
	pp.sortDropDownZtoA();
	Thread.sleep(3000);
	rp.generateReportsMethod("Sorted Z to A SS", "Product sorted Z to A displayed");
	pp.sortDropDownLowToHigh();
	Thread.sleep(3000);
	rp.generateReportsMethod("Sorted Low to High SS", "Product sorted Low to High displayed");
	pp.sortDropDownHighToLow();
	Thread.sleep(4000);
	rp.generateReportsMethod("Sorted High to Low SS", "Product sorted High to Low displayed");
}

@Test(priority=4)
public void addToCartverification() throws InterruptedException, IOException, DocumentException {
	pp.clickOnFirstAddToCart();
	Thread.sleep(2000);
	rp.generateReportsMethod("First Add to cart SS", "First product added to cart and Remove displayed and cart count is 1");
	String cartCountNo = pp.returnCartBadge().getText();
	Assert.assertEquals(cartCountNo, "1");
	pp.clickOnLastAddToCart();
	rp.generateReportsMethod("Last Add to cart SS", "Last product added to cart and Remove displayed and cart count is 2");
	Thread.sleep(2000);
	cartCountNo = pp.returnCartBadge().getText();
	Assert.assertEquals(cartCountNo, "2");
	Thread.sleep(2000);
	pp.clickTrolly();
	rp.generateReportsMethod("cart page SS", "cart page displayed");
}
@Test(priority=5)
	public void cartPageFunctionalities() throws InterruptedException, IOException, DocumentException{
	cp = new CartPage(driver);
 totalcheckOutPrice = 	cp.findTotalPrice();
 Thread.sleep(2000);
 cp.clickCheckoutButton();
 rp.generateReportsMethod("checkoutpage SS", "Checkout page displayed");
 Thread.sleep(2000);
}
@Test(priority=6, dataProvider = "userDataProviderMethod", dataProviderClass = DataPackage.UserDetailsClass.class)
public void checkoutPageFunction(String fname, String lname, String Zip) throws InterruptedException, IOException, DocumentException {
	cop = new CheckOutPage(driver);
	cop.fillCheckOutDetails(fname, lname, Zip);
	rp.generateReportsMethod("detail entered SS", "Entered details displayed");
	Thread.sleep(2000);
	cop.clickOnContinueButton();
	Thread.sleep(2000);
	rp.generateReportsMethod("OverView SS", "OverView page displayed");
	
}
@Test(priority = 7)
public void overViewPage() throws InterruptedException, IOException, DocumentException {
	op = new OverViewPage(driver);
	Double grandTotal = op.returnTotal();
	System.out.println(grandTotal+" "+totalcheckOutPrice);
	Assert.assertEquals(grandTotal, totalcheckOutPrice);
	Thread.sleep(2000);
	rp.generateReportsMethod("Total SS", "Total amount displayed");
	op.clickOnFinishBtn();
}

@Test(priority = 8)
public void finishPageFunctionalities() throws InterruptedException, IOException, DocumentException {
	fp = new FinishPage(driver);
	fp.verifyThankYouMsg();
	rp.generateReportsMethod("Thank you Modal SS", "Thank you Modal displayed");
	Thread.sleep(2000);
	fp.clickOnBackToHomeBtn();
	Thread.sleep(2000);
	 currentURL = driver.getCurrentUrl();
		String ProductsPageURL= prop.getProperty("ProductsPageURl");
		Assert.assertEquals(ProductsPageURL, currentURL);
		rp.generateReportsMethod("Product page again SS", "Product page displayed");
		rp.closeAllReports();
	
}
}
