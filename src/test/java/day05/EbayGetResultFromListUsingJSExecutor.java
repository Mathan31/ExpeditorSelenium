package day05;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class EbayGetResultFromListUsingJSExecutor {
	
	public static WebDriver driver;
	public static int browser = 1; // 1 - Chrome, 2 - Edge, 3 - FF, 4 - Safari
	public static String sURL = "https://www.ebay.com/";
	
	public static void main(String[] args) {
		invokeBrowser();
		browserSettings();
		navigateURL();
		getPageInfo();
		searchText("iPhone", "Cell Phones & Accessories");
		getSearchResult();
		//closeBrowser();
	}
	
	public static void invokeBrowser() {
		switch (browser) {
		case 1:
			System.out.println("User option is : "+browser+ ", So invoking chrome browser");
			driver = new ChromeDriver();
			break;
		case 2:
			System.out.println("User option is : "+browser+ ", So invoking edge browser");
			driver = new EdgeDriver();
			break;
		case 3:
			System.out.println("User option is : "+browser+ ", So invoking firefox browser");
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("User option is wrong : "+browser+ ", So invoking default chrome browser");
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
		// To get the title of the page
		String title = driver.getTitle();
		System.out.println("Page title is : "+title);
		// To get the current url of the page
		String url = driver.getCurrentUrl();
		System.out.println("URL is : "+url);
	}
	
	public static void searchText(String prodName,String prodCatagory) {
		WebElement oSearchText,oCatagoryDropdown,oSearchBtn;
		oSearchText = driver.findElement(By.id("gh-ac"));
		oSearchText.sendKeys(prodName);
		
		oCatagoryDropdown = driver.findElement(By.id("gh-cat"));
		Select oSelect = new Select(oCatagoryDropdown);
		oSelect.selectByVisibleText(prodCatagory);
		
		oSearchBtn = driver.findElement(By.id("gh-btn"));
		oSearchBtn.click();
		
	}
	
	public static void getSearchResult() {
		WebElement oResultText;
		oResultText = driver.findElement(By.xpath("(//h1[@class='srp-controls__count-heading']/span)[1]"));
		String resultText = oResultText.getText();
		resultText = resultText.replaceAll("[^0-9]", "");
		System.out.println("Result is : "+resultText);
		int iResult = Integer.parseInt(resultText);
		if(iResult > 0) {
			System.out.println("Result list is existing");
			getResultListProductName();
		}else {
			System.out.println("Result list is not existing");
		}
	}
	

	public static void getResultListProductName() {
		List<WebElement> resultLists = driver.findElements(By.xpath("//ul[@class='srp-results srp-list clearfix']/li[contains(@class,'s-item s-item')]"));
		for (WebElement result : resultLists) {
			WebElement productName = result.findElement(By.xpath(".//a[@class='s-item__link']//span[@role='heading']"));
			System.out.println("Product Name : "+productName.getText());
			int x = productName.getRect().getX();
			int y = productName.getRect().getY();
			scrollUsingJS(x, y);
		}
		scrollUsingJS(0, 0);
	}
	
	public static void scrollUsingJS(int x,int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String jsCode = String.format("window.scrollTo(%d,%d)", x,y);
		js.executeScript(jsCode);
	}
	
	
	public static void closeBrowser() {
		//driver.close();
		driver.quit();
	}
	
	
}
