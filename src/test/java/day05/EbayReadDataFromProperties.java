package day05;

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

import wrapper.ReadDataFromPropertyFile;



public class EbayReadDataFromProperties {
	
	public static String fileName = "Environment";
	public static WebDriver driver;
	public static String sURL = ReadDataFromPropertyFile.readDataFromPropertyFileBasedOnKey(fileName, "url");
	public static int iBrowserType = Integer.parseInt(ReadDataFromPropertyFile.readDataFromPropertyFileBasedOnKey(fileName, "browser")); // 1 - Chrome, 2 - Edge, 3 - FF, 4 - IE
	public static void main(String[] args) {
		invokeBrowser();
		browserSettings();
		navigateURL();
		getPageInfo();
		productSearch("iPhone", "Cell Phones & Accessories");
		
		//closeBrowser();
	}

	public static void invokeBrowser() {
		switch (iBrowserType) {
		case 1:
			System.out.println("User option is : "+iBrowserType+", So invoking the Chrome browser.");
			driver = new ChromeDriver();
			break;
		case 2:
			System.out.println("User option is : "+iBrowserType+", So invoking the Edge browser.");
			driver = new EdgeDriver();
			break;
		case 3:
			System.out.println("User option is : "+iBrowserType+", So invoking the FireFox browser.");
			driver = new FirefoxDriver();
			break;
		case 4:
			System.out.println("User option is : "+iBrowserType+", So invoking the IE browser.");
			driver = new InternetExplorerDriver();
			break;
		default:
			System.out.println("User option is wrong: "+iBrowserType+", So invoking the default Chrome browser.");
			driver = new ChromeDriver();
			break;
		}
	}
	
	public static void browserSettings() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	public static void navigateURL() {
		driver.get(sURL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public static void getPageInfo() {
		String title = driver.getTitle();
		System.out.println("Title is : "+title);
		String url = driver.getCurrentUrl();
		System.out.println("Current URL is : "+url);
	}
	
	public static void productSearch(String prodName,String prodCatagory) {
		WebElement oSearchText,oCatagorySelect,oSearcButton;
		oSearchText = driver.findElement(By.xpath("//input[@id='gh-ac']"));
		oSearchText.sendKeys(prodName);
		
		oCatagorySelect = driver.findElement(By.xpath("//select[@id='gh-cat']"));
		Select oSelect = new Select(oCatagorySelect);
		oSelect.selectByVisibleText(prodCatagory);
		
		oSearcButton = driver.findElement(By.xpath("//input[@id='gh-btn']"));
		oSearcButton.click();
	}
	
	public static void getSearchResult() {
		WebElement oResult;
		oResult = driver.findElement(By.xpath("(//h1[@class='srp-controls__count-heading']/span)[1]"));
		String result = oResult.getText();
		String numberOnly = result.replaceAll("[^0-9]", "");
		int iResult = Integer.parseInt(numberOnly);
		System.out.println("Result in number is : "+iResult);
		if(iResult > 0) {
			System.out.println("Result List is existing");
		}else {
			System.out.println("Result is ZERO");
		}
	}
	
	public static void closeBrowser() {
		driver.quit();
	}
		
}
