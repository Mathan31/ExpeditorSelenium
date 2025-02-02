package testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class P4_AmazonChild extends P4_BaseClass{
	
	public  String sURL = "https://www.amazon.in/";
	
	@BeforeTest
	public void browserSetup() {
		iBrowserType = 1;
	} 

	@Test(priority = 1)
		public  void browserSetting() {
			driver.manage().window().maximize(); // To maximize the browser
			driver.manage().deleteAllCookies(); // It will delete all the cookies
		}
		
		@Test(priority = 2)
		public  void navigateURL() {
			driver.navigate().to(sURL);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30)); // Wait until page loads
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Wait until element loaded in DOM .
		}
		@Test(priority = 3)
		public  void getPageInfo() {
			System.out.println("Page Title is : "+driver.getTitle());
			System.out.println("Page URL is : "+driver.getCurrentUrl());
			System.out.println("Window ID is : "+driver.getWindowHandle());
			
		}
		
		@Test(priority = 4) 
		public  void searchProduct() {
			WebElement oProduct,oDropDown,oButton;
			oProduct = driver.findElement(By.id("twotabsearchtextbox"));
			oProduct.clear();
			oProduct.sendKeys("iPhone");
			
			oDropDown = driver.findElement(By.id("searchDropdownBox"));
			Select oSelect = new Select(oDropDown);
			oSelect.selectByVisibleText("Electronics");
			
			oButton = driver.findElement(By.id("nav-search-submit-button"));
			oButton.click();
		}
		
		@Test(priority =5)
		public  void getSearchResult() {
			WebElement oResult;
			oResult = driver.findElement(By.xpath("(//h2[@class='a-size-base a-spacing-small a-spacing-top-small a-text-normal']/span)[1]"));
			String sResult = oResult.getText(); //530,000
		    System.out.println("Result is : "+sResult);
		}

}
