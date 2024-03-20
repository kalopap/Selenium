package com.utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSettings {
	
	public static final String URL = "https://rahulshettyacademy.com/client";
	WebDriver driver = new ChromeDriver();
	
	
	
	//invoke the browser 
	public void invokeBrowser() {
		
		System.out.println("Launching the website..");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);		
	}
	
	public String verifyTitleOfWebPage() {
		
		return driver.getTitle();
	}
	
	public WebElement findById(String locator) {
		return driver.findElement(By.id(locator));
		
	}
	
	public void typeInTextBox(WebElement elementInPage,String textToType) {
		elementInPage.sendKeys(textToType);
	}
	
	public void clickButton(WebElement element) {
		element.click();
	}
	
	public List<WebElement> findListByCssSelector(String locator) {
		return driver.findElements(By.cssSelector(locator));
	}
	
	public WebElement findByCssSelector(String locator) {
		return driver.findElement(By.cssSelector(locator));
	}
	public void cleanUp() {
		driver.close();
	}
}
