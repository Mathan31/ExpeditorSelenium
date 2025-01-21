package day03;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class JQueryDragAndDrop {
	
	public static WebDriver driver;
	public static int browser = 1; // 1 - Chrome, 2 - Edge, 3 - FF, 4 - Safari
	public static String sURL = "https://jqueryui.com/droppable/";
	
	public static void main(String[] args)  {
		invokeBrowser();
		browserSettings();
		navigateURL();
		getPageInfo();
		dragAndDrop();
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
		driver.get(sURL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
	
	public static void getPageInfo() {
		// To get the title of the page
		String title = driver.getTitle();
		System.out.println("Page title is : "+title);
		// To get the current url of the page
		String url = driver.getCurrentUrl();
		System.out.println("URL is : "+url);
	}
	
	public static void dragAndDrop() {
		WebElement oSrc,oDis;
		driver.switchTo().frame(0);
		oSrc = driver.findElement(By.id("draggable"));
		oDis = driver.findElement(By.id("droppable"));
		Actions actions = new Actions(driver);
		actions.dragAndDrop(oSrc, oDis).perform();
		
		  //actions.clickAndHold(oSrc) .moveToElement(oDis) .release() .perform();
		 
//		int x = oDis.getRect().getX();
//		int y = oDis.getRect().getY();
//		actions.clickAndHold(oSrc)
//		       .moveByOffset(x, y)
//		       .release()
//		       .perform();
	}
	
	
	
	public static void closeBrowser() {
		//driver.close();
		driver.quit();
	}
	
	
}
