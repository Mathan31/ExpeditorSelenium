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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import wrapper.ScreenShotSession;


//Modular Framework
public class P6_EbayTestNGParametersXMLLevel {
	
	public  WebDriver driver;
	public  String sURL = "https://www.ebay.com/";
	//public  int iBrowserType = 8; // 1 - Chrome, 2 - Edge, 3 - FF, 4 - IE
	
	@Parameters("browser")
	@BeforeClass
	public  void invokeBrowser(@Optional("2") int browserType) {
		switch (browserType) {
		case 1:
			System.out.println("User option is : "+browserType+", So invoking the Chrome browser.");
			driver = new ChromeDriver();
			break;
		case 2:
			System.out.println("User option is : "+browserType+", So invoking the Edge browser.");
			driver = new EdgeDriver();
			break;
		case 3:
			System.out.println("User option is : "+browserType+", So invoking the FireFox browser.");
			driver = new FirefoxDriver();
			break;
		case 4:
			System.out.println("User option is : "+browserType+", So invoking the IE browser.");
			driver = new InternetExplorerDriver();
			break;
		default:
			System.out.println("User option is wrong: "+browserType+", So invoking the default Chrome browser.");
			driver = new ChromeDriver();
			break;
		}
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
	
	@Parameters({"productName","productCatagory"})
	@Test(priority = 4)
	public  void productSearch(@Optional("iPhone") String prodName,@Optional("Cell Phones & Accessories") String prodCat) {
		WebElement oSearchText,oCatagorySelect,oSearcButton;
		oSearchText = driver.findElement(By.xpath("//input[@id='gh-ac']"));
		oSearchText.sendKeys(prodName);
		
		oCatagorySelect = driver.findElement(By.xpath("//select[@id='gh-cat']"));
		Select oSelect = new Select(oCatagorySelect);
		oSelect.selectByVisibleText(prodCat);
		
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
	
	@AfterClass
	public  void closeBrowser() {
		driver.quit();
	}
		
}
