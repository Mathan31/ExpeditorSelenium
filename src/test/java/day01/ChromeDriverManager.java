package day01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager implements FactoryInterface{

	@Override
	public WebDriver driverManager() {
		return new ChromeDriver();
		
	}

}
