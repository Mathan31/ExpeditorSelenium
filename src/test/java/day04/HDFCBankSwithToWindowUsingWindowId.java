package day04;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import javax.xml.xpath.XPath;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HDFCBankSwithToWindowUsingWindowId {
	
	public static WebDriver driver;
	public static String sBrowser = "Safari"; // Chrome,Edge,Firefox
	public static String sURL = "https://www.hdfcbank.com/";

	public static void main(String[] args) throws InterruptedException {
		invokeBrowser();
		browserSettings();
		navigateURL();
		getPageInfo();
		swithToWindowBasedOnId();
		closeBrowser();
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
	 */

	public static void swithToWindowBasedOnId() throws InterruptedException {
		WebElement oPension,oMutual;
		WebDriverWait oWait = new WebDriverWait(driver, Duration.ofSeconds(20));
		oWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='HDFC Pension']")));
		Actions actions = new Actions(driver);
		oPension = driver.findElement(By.xpath("//span[text()='HDFC Pension']"));
		actions.moveToElement(oPension).perform();
		System.out.println("Before clicking on pension link the count is : "+driver.getWindowHandles().size()); //1
		oPension.click();
		oWait.until(ExpectedConditions.numberOfWindowsToBe(2));
		System.out.println("After clicking on pension link the count is : "+driver.getWindowHandles().size()); //2
		// Swith to window using window id
		//driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
		swithToWindowUsingWindowID();
		getPageInfo();
		oWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='HDFC Mutual Fund']")));
		oMutual = driver.findElement(By.xpath("//a[text()='HDFC Mutual Fund']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//p[contains(text(),'Copyright 2024 All rights reserved')]")));
		Thread.sleep(2000);
		System.out.println("Before clicking on mutual link the count is : "+driver.getWindowHandles().size()); // 2
		oMutual.click();
		oWait.until(ExpectedConditions.numberOfWindowsToBe(3));
		System.out.println("After clicking on pension link the count is : "+driver.getWindowHandles().size()); //3
		//driver.switchTo().window(driver.getWindowHandles().toArray()[2].toString());
		swithToWindowUsingWindowID();
		getPageInfo();
		driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
		getPageInfo();
		driver.close();
		System.out.println("After closing home page the count is : "+driver.getWindowHandles().size()); //2
	}
	
	public static void swithToWindowUsingWindowID() {
		String newWindow = "";
		String currentWindow = driver.getWindowHandle();
		Set<String> allWindow = driver.getWindowHandles();
		for (String window : allWindow) {
			if(!currentWindow.equals(window)) {
				newWindow = window;
			}
		}
		driver.switchTo().window(newWindow);
	}
	
	public static void closeBrowser() {
		driver.quit();
	}

}
