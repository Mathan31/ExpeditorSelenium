package day07;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class HerokuAppFileUpload {
	
	public static WebDriver driver;
	public static String sURL = "https://the-internet.herokuapp.com/upload";
	public static String browserName = "chrome"; // Chrome,Firefox,Edge,IE,Safari
	
	/**
	 * Diff ways of handling the file upload ?
	 * 		- Sendkeys (When the type attribute value is "file").
	 * 		- Robot class from Java AWT
	 * 		- AutoIT (VB Script)
	 */

	public static void main(String[] args) throws Exception {
		invokeBrower();
		browserSettings();
		navigateURL();
		getPageInfo();
		//suploadUsingSendkeys();
		uploadUsingRobot(); 
		//closeBrowser();
	}
	
	public static void invokeBrower() {
		switch (browserName.toLowerCase()) {
		case "chrome":
			System.out.println("User option is : "+browserName+", So invoking chrome browser");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.out.println("User option is : "+browserName+", So invoking firefox browser");
			driver = new FirefoxDriver();
			break;
		case "edge":
			System.out.println("User option is : "+browserName+", So invoking edge browser");
			driver = new EdgeDriver();
			break;
		case "ie":
			System.out.println("User option is : "+browserName+", So invoking ie browser");
			driver = new InternetExplorerDriver();
			break;

		default:
			System.out.println("User option is wrong : "+browserName+", So invoking the default chrome browser");
			driver = new ChromeDriver();
			break;
		}
	}
	
	public static void browserSettings() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	public static void navigateURL() {
		//driver.get(sURL);
		driver.navigate().to(sURL);
//		driver.navigate().back();
//		driver.navigate().refresh();
//		driver.navigate().forward();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public static void getPageInfo() {
		String title = driver.getTitle();
		System.out.println("Title is : "+title);
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current url is : "+currentUrl);
		String windowHandle = driver.getWindowHandle();
		System.out.println("Window handler name : "+windowHandle);
	}

	public static void uploadUsingSendkeys() throws Exception  {
		WebElement upload;
		upload = driver.findElement(By.id("file-upload"));
		upload.sendKeys("C:\\Users\\user\\Desktop\\AppliationDetails.txt");
		WebElement button;
		button = driver.findElement(By.id("file-submit"));
		 Actions builder = new Actions(driver);
		 builder.moveToElement(button).click().perform();
		 Thread.sleep(2000);
	}
	

	public static void uploadUsingRobot() throws Exception {
		// copying File path to Clipboard
		// creating object of Robot class
		WebElement button;
		button = driver.findElement(By.id("file-upload"));
		//button.submit();
		 Actions builder = new Actions(driver);
		 builder.moveToElement(button).click().perform();
		 Thread.sleep(2000);
	    Robot rb = new Robot();
	    StringSelection str = new StringSelection("C:\\Users\\user\\Desktop\\AppliationDetails.txt");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
	 // press Control+V for pasting

	     rb.keyPress(KeyEvent.VK_CONTROL);
	     rb.keyPress(KeyEvent.VK_V);
	    // release Contol+V for pasting
	    rb.keyRelease(KeyEvent.VK_CONTROL);
	    rb.keyRelease(KeyEvent.VK_V);
	    Thread.sleep(2000);
	    // for pressing and releasing Enter
	    rb.keyPress(KeyEvent.VK_ENTER);
	    rb.keyRelease(KeyEvent.VK_ENTER);
	}
	
	
	public static void closeBrowser() {

		driver.quit();
	}
	

}
