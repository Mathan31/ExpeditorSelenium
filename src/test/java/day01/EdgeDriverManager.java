package day01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverManager implements FactoryInterface{

	@Override
	public WebDriver driverManager() {
		return new EdgeDriver();
		
	}

}
