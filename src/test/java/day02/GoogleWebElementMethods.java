package day02;

import java.time.Duration;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class GoogleWebElementMethods {
	
	public static WebDriver driver;
	public static String sURL = "https://www.google.com/";
	public static int iBrowserType = 8; // 1 - Chrome, 2 - Edge, 3 - FF, 4 - IE

	public static void main(String[] args) throws Exception {
		invokeBrowser();
		browserSettings();
		navigateURL();
		getPageInfo();
		googleSearch("Amazon");
		getPageInfo();
		closeBrowser();
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
	
	public static void googleSearch(String searchText) throws Exception {
		WebElement oSearchText,oSearchBtn,oResultLink,oLink,oLogo;
		oLogo = driver.findElement(By.xpath("//a[text()='Gmail']"));
		String text = oLogo.getText();
		System.out.println("Gmail text is : "+text);
		int x = oLogo.getRect().getX();
		int y = oLogo.getRect().getY();
		System.out.println("X-Coordinate is : "+x);
		System.out.println("Y-Coordinate is : "+y);
		oLink = driver.findElement(By.xpath("//a[text()='தமிழ்']"));
		String linkColor = oLink.getCssValue("color");
		System.out.println("Color is : "+linkColor);
		String fontSize = oLink.getCssValue("font-size");
		System.out.println("Size is : "+fontSize);
		oSearchText = driver.findElement(By.name("q"));
		oSearchText.clear();
		oSearchText.sendKeys(searchText);
		
		oSearchBtn = driver.findElement(By.name("btnK"));
		oSearchBtn.click();
		
		//oResultLink = driver.findElement(By.linkText("Deals on Smart TVs"));
		//oResultLink.click();
		
		/**
		 * Diff b/w click() and submit()
		 * click() 
		 * ======
		 * --> Can be performed for any clickable element presence in any where.
		 * --> Click this element. If this causes a new page to load, you should discard all references to
		 * this element and any further operations performed on this element will throw a
		 * StaleElementReferenceException.
		 * --> Note that if click() is done by sending a native event (which is the default on most
		 * browsers/platforms) then the method will _not_ wait for the next page to load and the caller
		 * should verify that themselves.
		 * --> There are some preconditions for an element to be clicked. The element must be visible, and
		 * it must have a height and width greater than 0.
		 * submit() 
		 * =======
		 * 		--> Can be performed for the button presence inside the form tag.
		 * 		--> If this current element is a form, or an element within a form, then this will be submitted to
		 * the remote server. If this causes the current page to change, then this method will block until
		 * the new page is loaded.
		 */
		//oSearchBtn.click();
			}
	
	public static void closeBrowser() {
		driver.quit();
	}
		
}
