package day05;

import java.time.Duration;
import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SalesForceSignUpShadowRoot {
	
	public static WebDriver driver;
	public static String sBrowser = "Safari"; // Chrome,Edge,Firefox
	public static String sURL = "https://developer.salesforce.com/signup";

	public static void main(String[] args) throws InterruptedException {
		invokeBrowser();
		browserSettings();
		navigateURL();
		getPageInfo();
		handleShadowDOM();
		//closeBrowser();
	}
	
	public static void invokeBrowser() {
		switch (sBrowser.toLowerCase()) {
		case "chrome":
			System.out.println("User option is "+sBrowser+",So invoking chrome browser");
			driver = new ChromeDriver();
			break;
		case "edge":
			System.out.println("User option is "+sBrowser+",So invoking edge browser");
			driver = new EdgeDriver();
			break;
		case "firefox":
			System.out.println("User option is "+sBrowser+",So invoking firefox browser");
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("User option is wrong "+sBrowser+",So invoking the default chrome browser");
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); //Polling Every .5sec or 500ms
	}
	
	public static void getPageInfo() {
		System.out.println("Title is : " + driver.getTitle());
		System.out.println("Current URL is : " + driver.getCurrentUrl());
	}
	

	public static void handleShadowDOM() throws InterruptedException {
		//handlingShadowDOMSelenium3();
		handlingShadowDOMSelenium4();
	}
	
	public static void handlingShadowDOMSelenium3() {
		WebElement shodowText;
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String script = "return document.querySelector('#maincontent > div > section.right > div "
	+ "> dw-de-signup-form').shadowRoot.querySelector('form > dx-input:nth-child(1)')"
	+ ".shadowRoot.querySelector('#input')";
		shodowText = (WebElement)js.executeScript(script);
		shodowText.sendKeys("Mathan");
	}
	
	public static void handlingShadowDOMSelenium4() {
		WebElement shodowText;
		shodowText = driver.findElement(By.xpath("//dw-de-signup-form"))
				           .getShadowRoot()
				           .findElement(By.cssSelector("dx-input.full-name-field")).getShadowRoot()
				           .findElement(By.cssSelector("#input"));
		shodowText.sendKeys("Mathan");
	}
	
	
		
	
	public static void closeBrowser() {
		driver.quit();
	}

}
