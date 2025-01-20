package day01;

    import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleSearch {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); // It will maximize the browser
		driver.manage().deleteAllCookies();
		driver.get("https://www.google.com/");
		String title = driver.getTitle();
		System.out.println("Title is : "+title);
		String currentUrl = driver.getCurrentUrl();
		System.out.println("URL is : "+currentUrl);
		driver.findElement(By.name("q")).sendKeys("expeditors");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.name("btnK")).click();
		//driver.findElement(By.name("login")).click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.findElement(By.linkText("Careers")).click();
		//driver.close();
	}

}
