package testng;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import wrapper.ScreenShotSession;


//Modular Framework
public class P4_EbayChild extends P4_BaseClass{
	
	public  String sURL = "https://www.ebay.com/";
	
	@BeforeTest
	public void browserSetup() {
		iBrowserType = 2;
	}
	
	
	@Test(priority = 1)
	public  void browserSettings() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	@Test(priority = 2)
	public  void navigateURL() {
		driver.get(sURL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	@Test(priority = 3)
	public  void getPageInfo() {
		String title = driver.getTitle();
		System.out.println("Title is : "+title);
		String url = driver.getCurrentUrl();
		System.out.println("Current URL is : "+url);
	}
	
	@Test(priority = 4)
	public  void productSearch() {
		WebElement oSearchText,oCatagorySelect,oSearcButton;
		oSearchText = driver.findElement(By.xpath("//input[@id='gh-ac']"));
		oSearchText.sendKeys("iPhone");
		
		oCatagorySelect = driver.findElement(By.xpath("//select[@id='gh-cat']"));
		Select oSelect = new Select(oCatagorySelect);
		oSelect.selectByVisibleText("Cell Phones & Accessories");
		
		oSearcButton = driver.findElement(By.xpath("//button[@id='gh-search-btn']"));
		oSearcButton.click();
	}
	@Test(priority = 5)
	public  void validateSearchResult() throws Exception {
		WebElement oResultText;
		oResultText = driver.findElement(By.xpath("(//h1[@class='srp-controls__count-heading']/span)[1]"));
		String resultValue = oResultText.getText(); //14,000
		String replacedValue = resultValue.replaceAll("[^0-9]+", "");
		int iResult = Integer.parseInt(replacedValue);
		System.out.println(iResult);
		
	}
	
	
}
