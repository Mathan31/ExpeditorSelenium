package day02;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


//Modular Framework
public class W3CheckBox {
	
	public static WebDriver driver;
	public static String sURL = "https://www.w3.org/WAI/ARIA/apg/patterns/checkbox/examples/checkbox/";
	public static int iBrowserType = 8; // 1 - Chrome, 2 - Edge, 3 - FF, 4 - IE
	public static String checkBoxDynamicUsingPlaceHolder = "//div[text()='%s']";
	public static void main(String[] args) {
		invokeBrowser();
		browserSettings();
		navigateURL();
		getPageInfo();
		//clickOnSingleCheckBox();
		clickOnAnyCheckBoxDynamixTextUsingPlaceHolder("Tomato");
		//clickOnAnyCheckBox("Sprouts");
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
	
	public static void clickOnSingleCheckBox() {
		WebElement lettuce;
		lettuce = driver.findElement(By.xpath("//div[text()='Lettuce']"));
		lettuce.click();
	}
	
	public static void clickOnAnyCheckBox(String checkBox) {
		WebElement lettuce;
		lettuce = driver.findElement(By.xpath("//div[text()='"+checkBox+"']"));
		lettuce.click();
	}
	
	public static void clickOnAnyCheckBoxDynamixTextUsingPlaceHolder(String value) {
		WebElement lettuce;
		String formatedLocator = String.format(checkBoxDynamicUsingPlaceHolder, value);
		System.out.println("Dynamic Locator : "+formatedLocator);
		lettuce = driver.findElement(By.xpath(formatedLocator));
		lettuce.click();
	}
	
	public static void closeBrowser() {
		driver.quit();
	}
		
}
