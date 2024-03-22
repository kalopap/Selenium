package com.utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestSettings {
	
	public static final String URL = "https://rahulshettyacademy.com/client";
	WebDriver driver = new ChromeDriver();
	//WebDriver driver = new FirefoxDriver();
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	
	
	//invoke the browser 
	public void invokeBrowser() {
		
		System.out.println("Launching the website..");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(URL);		
	}
	
	public String verifyTitleOfWebPage() {
		
		return driver.getTitle();
	}
	
	public WebElement findById(String locator) {
		return driver.findElement(By.id(locator));
		
	}
	
	public WebElement findByXpath(String locator) {
		return driver.findElement(By.xpath(locator));
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
	
	public void findElementFromInputList(String locator) {
		
	}
	
	public void scrollToTheElementAndClick(String locator) {
		WebElement element = driver.findElement(By.cssSelector(locator));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public void scrollElementIntoView(String locator) {
		WebElement element = driver.findElement(By.cssSelector(locator));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void scrollDownThePage() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void waitForWebElementToBeVisible(String locator) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
	}
	
	public void waitForWebElementToBeInvisible(String locator) {
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(locator))));
	}
	
	public void waitForElementToBeClickable(String locator) {
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
	}
	
	public void cleanUp() {
		driver.close();
	}
	
}
