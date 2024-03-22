package com.kalopap;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.utilities.TestSettings;

public class MainTest {

	static TestSettings setup = new TestSettings();
	static WebElement emailId;
	static WebElement password;
	static WebElement loginBtn;
	static WebElement country;
	static WebElement checkoutBtn;
	
	static List<WebElement> products;
	static List<WebElement> itemsInCart;
	
	public static void main(String[] args) {
		
		login();
		AddItemToCart();
		verifyItemsInCartAndCheckout();
		placeOrder();
		verifyFinalOrder();
		//setup.cleanUp();
	}
	
	public static void login() {
		setup.invokeBrowser();
		System.out.println("The title is.. " + setup.verifyTitleOfWebPage());
		
		//find email id textbox
		emailId = setup.findById("userEmail");
		setup.typeInTextBox(emailId, "qatest@kalopap.com");
		
		//input[@id='userPassword']
		
		password = setup.findById("userPassword");
		setup.typeInTextBox(password, "Mytest@123");
		
		loginBtn = setup.findById("login");
		loginBtn.click();
		
	}
	
	public static void AddItemToCart() {
		
		String productName;
		//wait for all the products to be visible
		setup.waitForWebElementToBeVisible(".mb-3");
		//get all the products present
		products = setup.findListByCssSelector(".mb-3");

		//iterate through the elements
		System.out.println("Total products found -" + products.size());

		for(WebElement product : products) {
			productName = product.findElement(By.cssSelector("b")).getText();
			System.out.println(productName);
			if(productName.equals("ADIDAS ORIGINAL") || productName.equals("IPHONE 13 PRO")) {
				//click on Add to Cart button
				product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
				
				//wait for the 'Product added to cart' toast notification to appear after the item is added to cart
				setup.waitForWebElementToBeVisible("#toast-container");
				
				//wait for the 'loading' ring to go invisible
				setup.waitForWebElementToBeInvisible(".ng-animating");					
			}			
		}
		
		//CLick on the 'Cart' menu on the top
		System.out.println("CLick on the 'Cart' menu on the top");
		setup.findByCssSelector("[routerlink*='cart']").click();
	}
	
	public static void verifyItemsInCartAndCheckout() {
		//get the list of item names in the cart
		itemsInCart = setup.findListByCssSelector(".cartSection h3");
		for(WebElement item : itemsInCart) {
			//if(item.getText().equals("IPHONE 13 PRO") || item.getText().equals("ADIDAS ORIGINAL")) {
				Assert.assertTrue(item.getText().equals("IPHONE 13 PRO") || item.getText().equals("ADIDAS ORIGINAL"));
				System.out.println("The test for item "+ item.getText() + " is passed");
			//}
		}
		
	}
	
	public static void placeOrder() {
	
		setup.scrollToTheElementAndClick(".totalRow button");
		country = setup.findByCssSelector("input[placeholder*='Select Country']");
		setup.typeInTextBox(country, "Ind");
		
		List<WebElement> cntryList = setup.findListByCssSelector(".ta-results button span");
		System.out.println(cntryList.size());
		for(WebElement country : cntryList) {
			if(country.getText().equals("India")) {					
				country.click();	
				break;
			}
		}
		//click on Place order button
		setup.scrollToTheElementAndClick(".action__submit");
	}
	
	public static void verifyFinalOrder() {
		String finalMsg = setup.findByCssSelector(".hero-primary").getText();
		System.out.println(finalMsg);
		Assert.assertTrue(finalMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		setup.cleanUp();
		
	}
}
