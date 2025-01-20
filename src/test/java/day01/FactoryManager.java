package day01;

public class FactoryManager {
	
	public static FactoryInterface getDriver(String browserType) {
		switch (browserType.toLowerCase()) {
		case "chrome":
			return new ChromeDriverManager();
		
		case "edge":
			return new EdgeDriverManager();

		default:
			throw new IllegalArgumentException("Browser Name is not valid");
		}
	}

}
