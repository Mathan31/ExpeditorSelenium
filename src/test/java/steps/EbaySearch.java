package steps;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EbaySearch {
	
	public WebDriver driver;
	public String sURL = "https://www.ebay.com/";
	public int iBrowserType = 8; // 1 - Chrome, 2 - Edge, 3 - FF, 4 - IE
	
@Given("User can open chrome browser")
public void invokeChrome() {
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
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
}

@Given("User can open preferred browser {int}")
public void user_can_open_preferred_browser(Integer browser) {
	switch (browser) {
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
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
}


@Given("^User can open preferred multiple browsers ([1-9]+)$")
public void user_can_open_preferred_multiple_browsers(Integer browser) {
	switch (browser) {
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
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
}



@Given("Navigate to Ebay home page")
public void navigate_to_ebay_home_page() {
	driver.get(sURL);
	String title = driver.getTitle();
	System.out.println("Title is : "+title);
	String url = driver.getCurrentUrl();
	System.out.println("Current URL is : "+url);
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
}

@When("User search the product name and product catagory")
public void user_search_the_product_name_and_product_catagory() {
	WebElement oSearchText,oCatagorySelect;
	oSearchText = driver.findElement(By.xpath("//input[@id='gh-ac']"));
	oSearchText.sendKeys("iPhone");
	
	oCatagorySelect = driver.findElement(By.xpath("//select[@id='gh-cat']"));
	Select oSelect = new Select(oCatagorySelect);
	oSelect.selectByVisibleText("Cell Phones & Accessories");
	
	
}

@When("User can search the preferred product name as {string} and product catagory as {string}")
public void user_can_search_the_preferred_product_name_as_and_product_catagory_as(String prodName, String prodCatagory) {
	WebElement oSearchText,oCatagorySelect;
	oSearchText = driver.findElement(By.xpath("//input[@id='gh-ac']"));
	oSearchText.sendKeys(prodName);
	
	oCatagorySelect = driver.findElement(By.xpath("//select[@id='gh-cat']"));
	Select oSelect = new Select(oCatagorySelect);
	oSelect.selectByVisibleText(prodCatagory);
}

@When("^User can search the preferred multiple product name as (\\w+) and catagory as (\\w+)$")
public void user_can_search_the_preferred_multiple_product_name_as_java_and_catagory_as_books(String prodName, String prodCatagory) {
	WebElement oSearchText,oCatagorySelect;
	oSearchText = driver.findElement(By.xpath("//input[@id='gh-ac']"));
	oSearchText.sendKeys(prodName);
	
	oCatagorySelect = driver.findElement(By.xpath("//select[@id='gh-cat']"));
	Select oSelect = new Select(oCatagorySelect);
	oSelect.selectByVisibleText(prodCatagory);
}


@When("User can search the preferred multipl product name and multiple catagory as below")
public void user_can_search_the_preferred_multipl_product_name_and_multiple_catagory_as_below(DataTable dataTable) {
	List<List<String>> asLists = dataTable.asLists();
	for (List<String> list : asLists) {
		String prodName = list.get(0);
		String prodCatagory = list.get(1);
		WebElement oSearchText,oCatagorySelect;
		oSearchText = driver.findElement(By.xpath("//input[@id='gh-ac']"));
		oSearchText.clear();
		oSearchText.sendKeys(prodName);
		
		oCatagorySelect = driver.findElement(By.xpath("//select[@id='gh-cat']"));
		Select oSelect = new Select(oCatagorySelect);
		oSelect.selectByVisibleText(prodCatagory);
		click_on_search_button();
	}
}

@When("User can search the preferred multipl product name and multiple catagory as below list of map")
public void user_can_search_the_preferred_multipl_product_name_and_multiple_catagory_as_below_list_of_map(DataTable dataTable) {
	List<Map<String, String>> asMaps = dataTable.asMaps();
	for (Map<String, String> map : asMaps) {
		String prodName = map.get("ProductName");
		String prodCatagory = map.get("ProductCatagory");
		WebElement oSearchText,oCatagorySelect;
		oSearchText = driver.findElement(By.xpath("//input[@id='gh-ac']"));
		oSearchText.clear();
		oSearchText.sendKeys(prodName);
		
		oCatagorySelect = driver.findElement(By.xpath("//select[@id='gh-cat']"));
		Select oSelect = new Select(oCatagorySelect);
		oSelect.selectByVisibleText(prodCatagory);
		click_on_search_button();
	}
	
}





@When("click on search button")
public void click_on_search_button() {
	WebElement oSearcButton;
	oSearcButton = driver.findElement(By.xpath("//button[@id='gh-search-btn']"));
	oSearcButton.click();
}

@Then("User should able to see the search results")
public void user_should_able_to_see_the_search_results() {
	WebElement oResultText;
	oResultText = driver.findElement(By.xpath("(//h1[@class='srp-controls__count-heading']/span)[1]"));
	String resultValue = oResultText.getText(); //14,000
	String replacedValue = resultValue.replaceAll("[^0-9]+", "");
	int iResult = Integer.parseInt(replacedValue);
	System.out.println(iResult);
	if(iResult > 0) {
		System.out.println("Search result is available");
	}else {
		System.out.println("Search result is ZERO!");
	}
}

@Then("Close browser")
public void close_browser() {
   driver.quit();
}


}
