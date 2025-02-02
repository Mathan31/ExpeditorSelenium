package day06;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariOptions;

public class BrowserSettings {

	public static void main(String[] args) {
		
		ChromeOptions option = new ChromeOptions();
		//EdgeOptions option = new EdgeOptions();
		//option.addArguments("start-maximized");
		option.addArguments("--start-maximized");
		option.addArguments("--incognito");
	    //option.addArguments("start-maximized","incognito");
		option.setAcceptInsecureCerts(true);
		//option.addArguments("--headless=new");
//		List<String> optionList = new ArrayList<>();
//		optionList.add("--start-maximized");
//		optionList.add("--incognito");
//		optionList.add("--headless");
		//option.addArguments(optionList);
		
//		Proxy proxy = new Proxy();
//		proxy.setHttpProxy("100.200.300.400");
//		option.setProxy(proxy);
		
		DesiredCapabilities dc = new DesiredCapabilities();
//		dc.setPlatform(Platform.WINDOWS);
		
		WebDriver driver = new ChromeDriver(option);
		//Override methods from ChromeDriver,methods from Webdriver and Object class methods
		driver.manage().deleteAllCookies(); // To delete all the cookies in the browser
		
		driver.get("https://www.credosystemz.com/"); // To launch the page
		
		String title = driver.getTitle();
		System.out.println("Title is : "+title);
		
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current URL is : "+currentUrl);
		
		driver.quit();
	}

}
