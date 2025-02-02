package day05;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FaceBookLoginJSExecutor {
	
	public static WebDriver driver;
	public static int browser = 1; // 1 - Chrome, 2 - Edge, 3 - FF, 4 - Safari
	public static String sURL = "https://www.facebook.com/";
	
	public static void main(String[] args) {
		invokeBrowser();
		browserSettings();
		navigateURL();
		getPageInfo();
		loginFaceBook();
		//closeBrowser();
	}
	
	public static void invokeBrowser() {
		switch (browser) {
		case 1:
			System.out.println("User option is : "+browser+ ", So invoking chrome browser");
			driver = new ChromeDriver();
			break;
		case 2:
			System.out.println("User option is : "+browser+ ", So invoking edge browser");
			driver = new EdgeDriver();
			break;
		case 3:
			System.out.println("User option is : "+browser+ ", So invoking firefox browser");
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("User option is wrong : "+browser+ ", So invoking default chrome browser");
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
//		driver.navigate().refresh();
//		driver.navigate().back();
//		driver.navigate().forward();
		System.out.println("Window ID : "+driver.getWindowHandle());
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		}
	
	public static void getPageInfo() {
		// To get the title of the page
		String title = driver.getTitle();
		System.out.println("Page title is : "+title);
		// To get the current url of the page
		String url = driver.getCurrentUrl();
		System.out.println("URL is : "+url);
	}
	
	public static void loginFaceBook() {
		WebElement oEmail,oLogin;
		JavascriptExecutor js = (JavascriptExecutor)driver;
		oEmail = (WebElement) js.executeScript("return document.querySelector('#email')");
		hightLightElement(driver, oEmail);
		oEmail.sendKeys("CredoSystemz");
		js.executeScript("document.querySelector('#pass').value='Testing123';");
		oLogin = driver.findElement(By.xpath("//button[@name='login']"));
		hightLightElement(driver, oLogin);
		js.executeScript("arguments[0].click();", oLogin);
		
	}
	
	public static void hightLightElement(WebDriver driver,WebElement webElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red';", webElement);
	}
	
	public static void closeBrowser() {
		//driver.close();
		driver.quit();
	}
	
	
}
