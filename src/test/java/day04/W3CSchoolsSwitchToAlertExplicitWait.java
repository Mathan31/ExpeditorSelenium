package day04;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import javax.xml.xpath.XPath;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class W3CSchoolsSwitchToAlertExplicitWait {
	
	public static WebDriver driver;
	public static String sBrowser = "Safari"; // Chrome,Edge,Firefox
	public static String sURL = "https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_prompt";

	public static void main(String[] args) throws InterruptedException {
		invokeBrowser();
		browserSettings();
		navigateURL();
		getPageInfo();
		swithToAlert();
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
	
	/**
	 * Diff b/w Implicit and Explicit Wait
	 * - Implicit will come in to act when we call findelement. It will wait for provided sec and 
	 * 	 its polling time 500ms or .5 sec.
	 * - Implicit wait will throw "NO SUCH ELEMENT" exception
	 * - Implicit will wait until element loaded in the DOM, It wont check the element state (clickable).
	 * - Explicit will be waiting for certain conditions to  be satisfied .It will wait for provided sec and 
	 * 	 its polling time 500ms or .5 sec.
	 * - Explicit wait will throw "TIMEOUT" exception
	 * - WebDriverWait is a child of Fluentwait
	 */

	public static void swithToAlert() throws InterruptedException {
		WebElement oBtn;
		WebDriverWait oWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.switchTo().frame("iframeResult");
		oBtn = driver.findElement(By.xpath("//button[text()='Try it']"));
		oBtn.click();
		oWait.until(ExpectedConditions.alertIsPresent());
//		oWait.ignoring(NoSuchElementException.class);
//		oWait.pollingEvery(Duration.ofSeconds(500));
		//Thread.sleep(3000);
		
		String alertText = driver.switchTo().alert().getText();
		System.out.println(alertText);
		driver.switchTo().alert().accept();
	}
	
	public static void closeBrowser() {
		driver.quit();
	}

}
