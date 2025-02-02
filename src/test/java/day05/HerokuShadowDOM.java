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

public class HerokuShadowDOM {
	
	public static WebDriver driver;
	public static String sBrowser = "Safari"; // Chrome,Edge,Firefox
	public static String sURL = "https://the-internet.herokuapp.com/shadowdom";

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
		handlingShadowDOMSelenium3();
		//handlingShadowDOMSelenium4();
	}
	
	public static void handlingShadowDOMSelenium3() {
		WebElement shodowText;
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String script = "return document.querySelector('#content > my-paragraph:nth-child(4)').shadowRoot.querySelector('p > slot')";
		shodowText = (WebElement)js.executeScript(script);
		System.out.println("Text fetched from shadow root : "+shodowText.getText());
	}
	
	public static void handlingShadowDOMSelenium4() {
		WebElement shodowText;
		shodowText = driver.findElement(By.xpath("//my-paragraph[1]"))
				           .getShadowRoot()
				           .findElement(By.cssSelector("p > slot"));
		System.out.println("Text fetched from shadow root : "+shodowText.getText());
	}
	
	
		
	
	public static void closeBrowser() {
		driver.quit();
	}

}
