package day03;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.locators.RelativeLocator.RelativeBy;

public class RelativeLocators {
	

	/**
	 * Webelement - Any Element presence in the web page can be called as WebElement .
	 * 
	 * Locators : We have different types of locators which is used to locate any elements in the webpage.
	 * 
	 */

	public static void main(String[] args) {
		// To invoke the Chrome browser
				WebDriver driver = new EdgeDriver();
				// To maximize the browser
				driver.manage().window().maximize();
				// To clear cookies and cache
				driver.manage().deleteAllCookies();
				// To navigate to any webpage
				driver.get("https://www.facebook.com/");
				// Sync up using Page Load Time
				
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				// To get the title of the page
				String title = driver.getTitle();
				System.out.println("Title is : " + title);
				// To get the currrent url
				String currentUrl = driver.getCurrentUrl();
				System.out.println("Current url is : " + currentUrl);
				// RelativeLocators
				RelativeBy passwordAbove = RelativeLocator.with(By.tagName("input")).above(By.id("pass"));
				driver.findElement(passwordAbove).sendKeys("credosystemz");
				
				RelativeBy nearEmail = RelativeLocator.with(By.tagName("input")).near(By.id("email"));
				driver.findElement(nearEmail).sendKeys("Testing123");
				
				// RelativeLocators Chaining
				RelativeBy buttonChaining = RelativeLocator.with(By.tagName("button")).below(By.id("pass"))
				               .above(By.linkText("Forgotten password?"));
				driver.findElement(buttonChaining).click();
				//driver.quit();
	}

}
