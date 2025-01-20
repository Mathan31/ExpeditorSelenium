package day01;

import org.openqa.selenium.WebDriver;

public class BrowserInvoke {
	public static WebDriver driver;
	
	public static void main(String[] args) {
		driver = FactoryManager.getDriver("chrome")
				.driverManager();
		driver.manage().window().maximize(); // It will maximize the browser
		driver.manage().deleteAllCookies();
		driver.get("https://ww  w.google.com/");
		String title = driver.getTitle();
		System.out.println("Title is : "+title);
		String currentUrl = driver.getCurrentUrl();
		System.out.println("URL is : "+currentUrl);
		driver.close();
	}

}
   