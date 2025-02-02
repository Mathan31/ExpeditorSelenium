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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


//Modular Framework
public class FlipkartAction {
	
	public static WebDriver driver;
	public static String sURL = "https://www.flipkart.com/";
	public static int iBrowserType = 8; // 1 - Chrome, 2 - Edge, 3 - FF, 4 - IE
	public static void main(String[] args) {
		invokeBrowser();
		browserSettings();
		navigateURL();
		getPageInfo();
		mouseOver();
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
	
		
	public static void mouseOver() {
		WebElement oElectronics,oSmartHome;
		oElectronics = driver.findElement(By.xpath("//span[text()='Electronics']"));
		
		Actions actions = new Actions(driver);
//		actions.moveToElement(oElectronics).perform();
//		oSmartHome = driver.findElement(By.xpath("//a[text()='Smart home']"));
//		oSmartHome.click();
		
//		actions.moveToElement(oElectronics).pause(Duration.ofSeconds(2))
//		.moveToElement(driver.findElement(By.xpath("//a[text()='Cameras & Accessories']")))
//		.pause(Duration.ofSeconds(2))
//		.moveToElement(driver.findElement(By.xpath("//a[text()='Sports & action']")))
//				.click().perform();
		
		actions.moveToElement(oElectronics).pause(Duration.ofSeconds(2)).perform();
		
		actions.moveToElement(driver.findElement(By.xpath("//a[text()='Cameras & Accessories']")))
		.pause(Duration.ofSeconds(2)).perform();
		
		actions.moveToElement(driver.findElement(By.xpath("//a[text()='Sports & action']")))
				.click().perform();
		
	}
	
		
	public static void closeBrowser() {
		driver.quit();
	}
		
}
