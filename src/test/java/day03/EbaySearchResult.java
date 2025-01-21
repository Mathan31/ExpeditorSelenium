package day03;

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


//Modular Framework
public class EbaySearchResult {
	
	public static WebDriver driver;
	public static String sURL = "https://www.ebay.com/";
	public static int iBrowserType = 8; // 1 - Chrome, 2 - Edge, 3 - FF, 4 - IE
	public static void main(String[] args) {
		invokeBrowser();
		browserSettings();
		navigateURL();
		getPageInfo();
		productSearch("iPhone", "Cell Phones & Accessories");
		//validateSearchResult();
		getSearchResultByIndexing();
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
		
		oSearcButton = driver.findElement(By.xpath("//button[@id='gh-search-btn']"));
		oSearcButton.click();
	}
	
	public static void validateSearchResult() {
		WebElement oResultText;
		oResultText = driver.findElement(By.xpath("(//h1[@class='srp-controls__count-heading']/span)[1]"));
		String resultValue = oResultText.getText(); //14,000
		String replacedValue = resultValue.replaceAll("[^0-9]+", "");
		int iResult = Integer.parseInt(replacedValue);
		System.out.println(iResult);
		if(iResult > 0) {
			getSearchResult();
		}else {
			System.out.println("Search result is ZERO!");
		}
	}
	
	public static void getSearchResult() {
		List<WebElement> linkLists = driver.findElements(By.xpath("//ul[@class='srp-results srp-list clearfix']/li[contains(@class,'s-item s-item')]"));
		for (WebElement linkList : linkLists) {
			WebElement linkText = linkList.findElement(By.xpath(".//a[@class='s-item__link']//span[@role='heading']"));
			String text = linkText.getText();
			System.out.println(text);
		}
	}
	
	public static void getSearchResultByIndexing() {
		List<WebElement> linkLists = driver.findElements(By.xpath("//ul[@class='srp-results srp-list clearfix']/li[contains(@class,'s-item s-item')]"));
		for(int i=1;i<=linkLists.size();i++) {
			WebElement linkIndex = driver.findElement(By.xpath("//ul[@class='srp-results srp-list clearfix']"
					+ "/li[contains(@class,'s-item s-item')]["+i+"]//a[@class='s-item__link']//span[@role='heading']"));
		System.out.println(linkIndex.getText());
		}
	}
		
	public static void closeBrowser() {
		driver.quit();
	}
		
}
