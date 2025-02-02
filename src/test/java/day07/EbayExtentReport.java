package day07;

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
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import wrapper.ScreenShotSession;


public class EbayExtentReport {
	
	public static WebDriver driver;
	public static int browser = 8; // 1 - Chrome, 2 - Firefox,3 - Edge,4 - IE,5 - Opera
	public static String sURL = "https://www.ebay.com/";
	public static ExtentSparkReporter oSpark;
	public static ExtentReports oReport;
	public static ExtentTest oTest;
	public static String sReport = "./reports/EbayReport.html";

	public static void main(String[] args) throws Exception {
		reportSetUp();
		invokeBrowser();
		browserSettings();
		navigateURL();
		getPageInfo();
		searchProduct("iPhone", "Cell Phones & Accessories");
		getSearchResult();
		closeBrowser();
	}
	public static void reportSetUp() {
		oSpark = new ExtentSparkReporter(sReport);
		oReport = new ExtentReports();
		oReport.attachReporter(oSpark);
	}
	
	
	public static void invokeBrowser() {
		oTest = oReport.createTest("Invoke Browser", "User can invoke prefered browser based on the input.");
		oTest.assignAuthor("Deepika");
		oTest.assignCategory("Smoke Testing");
		switch (browser) {
		case 1:
			System.out.println("User option is : "+browser+", So invoking chrome browser");
			driver = new ChromeDriver();
			oTest.info("User option is : "+browser+", So invoking chrome browser");
			break;
		case 2:
			System.out.println("User option is : "+browser+", So invoking firefox browser");
			driver = new FirefoxDriver();
			oTest.info("User option is : "+browser+", So invoking firefox browser");
			break;
		case 3:
			System.out.println("User option is : "+browser+", So invoking edge browser");
			driver = new EdgeDriver();
			oTest.info("User option is : "+browser+", So invoking edge browser");
			break;
		case 4:
			System.out.println("User option is : "+browser+", So invoking ie browser");
			driver = new InternetExplorerDriver();
			oTest.info("User option is : "+browser+", So invoking ie browser");
			break;

		default:
			System.out.println("User option is wrong : "+browser+", So invoking default chrome browser");
			driver = new ChromeDriver();
			oTest.info("User option is wrong : "+browser+", So invoking default chrome browser");
			break;
		}
	}
	
	public static void browserSettings() {
		oTest = oReport.createTest("Browser Settings", "User can change the browser settings.");
		oTest.assignAuthor("Srilatha");
		oTest.assignCategory("Sanity");
		driver.manage().window().maximize();
		oTest.info("User maximized the browser.");
		driver.manage().deleteAllCookies();
		oTest.info("User deleted all the browser cookies.");
	}
	
	public static void navigateURL() {
		oTest = oReport.createTest("Navigate URL", "User can navigate to prefered url.");
		oTest.assignAuthor("Mukesh");
		oTest.assignCategory("Smoke");
		driver.get(sURL);
		oTest.info("User navigated to : "+sURL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	public static void getPageInfo() throws Exception {
		oTest = oReport.createTest("Validate page information", "User is validating the title and url.");
		oTest.assignAuthor("Latchana");
		oTest.assignCategory("Sanity");
		System.out.println("Page title is : "+driver.getTitle());
		String title = driver.getTitle();
		oTest.info("Page title is : "+title);
		System.out.println("Page URL is : "+driver.getCurrentUrl());
		String currentUrl = driver.getCurrentUrl();
		oTest.info("Current URL is : "+currentUrl);
		if(title.equalsIgnoreCase("ebay")) {
			oTest.pass("User landed to the right page : "+title);
		}else {
			String imagePath = ScreenShotSession.takeScreenShotAsFileWithDynamicFileName(driver, "EbayTitle");
			oTest.fail("User landed to the wrong page", MediaEntityBuilder
					.createScreenCaptureFromPath(imagePath).build());
		}
	}
	
	public static void searchProduct(String searchTxt,String searchCat) {
		oTest = oReport.createTest("Search Product", "User can search for any product by providing the catagory.");
		oTest.assignAuthor("Deepika");
		oTest.assignCategory("Regression");
		WebElement oText,oDrop,oBtn;
		oText = driver.findElement(By.id("gh-ac"));
		oText.sendKeys(searchTxt);
		oTest.info("User entered the search product as : "+searchTxt);
		oDrop = driver.findElement(By.id("gh-cat"));
		Select select = new Select(oDrop);
		select.selectByVisibleText(searchCat);
		oTest.info("User selected the search catagory as : "+searchCat);
		oBtn = driver.findElement(By.xpath("//button[@id='gh-search-btn']"));
		oBtn.click();
		oTest.info("User clicked on search button");
	}
	
	public static void getSearchResult() throws Exception {
		oTest = oReport.createTest("Validate Search Result", "User can Validate the Search Results.");
		oTest.assignAuthor("Mukesh");
		oTest.assignCategory("Regression");
		WebElement oResult;
		oResult = driver.findElement(By.xpath("(//h1[@class='srp-controls__count-heading']/span)[1]"));
		String sResult = oResult.getText();
		sResult = sResult.replaceAll("[^0-9]", "");
		System.out.println("Search Result is : "+sResult);
		oTest.info("Search Result is : "+sResult);
		int iResult = Integer.parseInt(sResult);
		if(iResult > 0) {
			System.out.println("Search results are available....");
			List<WebElement> resultLists = driver.findElements(By.
					xpath("(//ul[@class='srp-results srp-list clearfix']/li[contains(@class,'s-item s-item')])"));
			for (WebElement resultList : resultLists) {
				WebElement product = resultList.findElement(By.xpath(".//a[@class='s-item__link']"));
				String productName = product.getText();
				System.out.println(productName);
				oTest.pass("Product name is : "+productName);
			}
		}else {
			System.out.println("Search results are not available....");
			String imagePath = ScreenShotSession.takeScreenShotAsFileWithDynamicFileName(driver, "ResultCount");
			oTest.fail("Search result is zero.", MediaEntityBuilder
					.createScreenCaptureFromPath(imagePath).build());
		}
	}
		
	public static void closeBrowser() {
		oReport.flush();
		driver.quit();
	}
}
