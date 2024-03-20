package com.kalopap;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.utilities.TestSettings;

public class MainTest {

	static TestSettings setup = new TestSettings();
	static WebElement emailId;
	static WebElement password;
	static WebElement loginBtn;
	
	static List<WebElement> products;
	
	public static void main(String[] args) {
		login();
		AddItemToCart();
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
		//get all the products present
		products = setup.findListByCssSelector(".mb-3");

		//iterate through the elements

		for(WebElement item : products) {
			if(item.getText().equals("ADIDAS ORIGINAL")) {
				item.click();
				break;
			}			
		}
		//click on Add to Cart button
		setup.findByCssSelector(".card-body button:last-of-type").click(); 
		
		//other way using Javastreams
		/*WebElement actualProduct = products.stream().filter(product->product.findElement(By.cssSelector("b"))
				.getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
		actualProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();*/
	}
	
}
