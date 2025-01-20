package day01;

    import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocatorsLearning {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); // It will maximize the browser
		driver.manage().deleteAllCookies();
		//driver.get("https://www.facebook.com//");
		driver.navigate().to("https://www.facebook.com/");
		String title = driver.getTitle();
		System.out.println("Title is : "+title);
		String currentUrl = driver.getCurrentUrl();
		System.out.println("URL is : "+currentUrl);
		driver.findElement(By.id("email")).sendKeys("seleniumtraining");
		driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("");
		driver.findElement(By.xpath("//a[text()='your destination']")).click();
		//driver.findElement(By.className("input.inputtext._55r1._6luy")).sendKeys("seleniumtraining");
		driver.findElement(By.name("pass")).sendKeys("testing123");
		//driver.findElement(By.name("login")).click();
		driver.findElement(By.linkText("Forgotten password?")).click();
		//driver.close();
		driver.quit();
	}

}
