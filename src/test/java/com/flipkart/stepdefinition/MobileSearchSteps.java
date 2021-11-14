package com.flipkart.stepdefinition;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MobileSearchSteps {
	static WebDriver driver;
	@Given("user lauches flipkart application")
	public void user_lauches_flipkart_application() {
	 WebDriverManager.chromedriver().setup();
	 driver = new ChromeDriver();
	 driver.get("https://www.flipkart.com/");
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}

	@Given("user enters credentials and click on login")
	public void user_enters_credentials_and_click_on_login() {
	    try {
	    	WebElement xbutton = driver.findElement(By.xpath("//button[text()='X']"));
	    	xbutton.isDisplayed();
	    	xbutton.click();
	    }catch (Exception e) {
	    	System.out.println("Popup is not displayed");
	    }
	}
	@When("user searching the mobile")
	public void user_searching_the_mobile() {
	   WebElement search = driver.findElement(By.name("q"));
	   search.sendKeys("Samsung",Keys.ENTER);
	}
static String parentURL;
	@When("user click on add to cart")
	public void user_click_on_add_to_cart() {
		WebElement mobileName = driver.findElement(By.xpath("(//div[contains(text(),'SAMSUNG')])[2]"));
		   mobileName.click();
		   
		    parentURL = driver.getWindowHandle();
		   Set<String> childURL = driver.getWindowHandles();
		   for(String child : childURL) {
			   if(!parentURL.equals(child)) {
			   driver.switchTo().window(child);
		   }
		   }
	
	driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();
	
	    
	}
	@Then("navigates into add to cart and check the order to updated")
	public void navigates_into_add_to_cart_and_check_the_order_to_updated() {
		driver.switchTo().window(parentURL);
		driver.findElement(By.xpath("//span[text()='cart']")).click();
		try {
	    	WebElement xbutton = driver.findElement(By.xpath("//button[text()='X']"));
	    	xbutton.isDisplayed();
	    	xbutton.click();
	    }catch (Exception e) {
	    	System.out.println("Popup is not displayed");
	    }
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Place Order']")).isDisplayed());
	}

 
}
