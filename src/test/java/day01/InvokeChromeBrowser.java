package day01;

    import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InvokeChromeBrowser {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); // It will maximize the browser
		driver.manage().deleteAllCookies();
		driver.get("https://www.google.com/");
		String title = driver.getTitle();
		System.out.println("Title is : "+title);
		String currentUrl = driver.getCurrentUrl();
		System.out.println("URL is : "+currentUrl);
		driver.close();
	}

}
