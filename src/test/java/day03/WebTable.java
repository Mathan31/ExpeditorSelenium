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
import org.openqa.selenium.support.ui.Select;


//Modular Framework
public class WebTable {
	
	public static WebDriver driver;
	public static String sURL = "https://cosmocode.io/automation-practice-webtable/";
	public static int iBrowserType = 8; // 1 - Chrome, 2 - Edge, 3 - FF, 4 - IE
	public static void main(String[] args) {
		invokeBrowser();
		browserSettings();
		navigateURL();
		getPageInfo();
		//getValueBasedOnRowCell(4, 2);
		getAllRowsNColsValues();
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
	
		
	public static void getValueBasedOnRowCell(int iRow,int col) {
		WebElement oTable;
		oTable = driver.findElement(By.xpath("//table[@id='countries']/tbody/tr["+iRow+"]/td["+col+"]"));
		System.out.println(oTable.getText());
	}
	
	public static void getAllRowsNColsValues() {
		WebElement oTable;
		oTable = driver.findElement(By.xpath("//table[@id='countries']/tbody"));
		List<WebElement> oRows = oTable.findElements(By.tagName("tr"));
		for (WebElement oRow : oRows) {
			List<WebElement> oCols = oRow.findElements(By.tagName("td"));
			for (WebElement oCol : oCols) {
				System.out.print(oCol.getText()+"\t");
			}
			System.out.println();
		}
		
	}
		
	public static void closeBrowser() {
		driver.quit();
	}
		
}
