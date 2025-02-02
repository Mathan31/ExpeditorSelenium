
package day06;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Duration;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Ebay_DataDriven {

	public static WebDriver driver;

	public static int iRow, iTotalRow, iCell, iTotalCell;
	public static String sExcelFile = "./data/Ebay.xlsx";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String sSheet, sSearchTxt;
		sSheet = "Search";
		pageNavigation();
		try {
			InputStream oFile = new FileInputStream(sExcelFile);
			XSSFWorkbook oExcel = new XSSFWorkbook(oFile);
			XSSFSheet oSheet = oExcel.getSheet(sSheet); 
			XSSFRow oRow;
			XSSFCell oCell;
			iTotalRow = oSheet.getLastRowNum();//3
			for (iRow = 1; iRow <= iTotalRow; iRow++) {
				oRow = oSheet.getRow(iRow);
				iTotalCell = oRow.getLastCellNum();
				for (iCell = 0; iCell < iTotalCell; iCell++) {
					oCell = oRow.getCell(iCell);
					sSearchTxt = oCell.getStringCellValue();
					searchProduct(sSearchTxt, "Cell Phones & Accessories");
					getMatch(sSearchTxt);
				}

			}
			oExcel.close();
			oFile.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();

	}
	public static void pageNavigation() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.ebay.com/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	}

	public static void searchProduct(String sTxt, String sCat) {
		WebElement oText, oBtn, oDropDown;
		//int size = driver.findElements(By.xpath("//*[@id='gh-ac']")).size();
		oText = driver.findElement(By.xpath("//*[@id='gh-ac']"));
		boolean oResult = oText.isDisplayed();
		oText.clear();
		oText.sendKeys(sTxt);
		oDropDown = driver.findElement(By.xpath("//*[@id='gh-cat']"));
		Select oSelect = new Select(oDropDown);
		oSelect.selectByVisibleText(sCat);

		oBtn = driver.findElement(By.xpath("//button[@id='gh-search-btn']"));
		oBtn.click();
	}
/**
 * 
 * @param sTxt (Product Name from Excel - Ex : iPhone,Lenovo,Samsung)
 * @throws Exception
 */
	public static void getMatch(String sTxt) throws Exception {
		WebElement oText, oProduct;
		Thread.sleep(2000);
		oText = driver.findElement(By.xpath("//*[@class='listingscnt']|//*[@class='srp-controls__count-heading']"));
		String sText = oText.getText();
		System.out.println("Search Result is : " + sText);
		sText = sText.replaceAll("[^0-9]", "").trim();
		int iText = Integer.parseInt(sText);
		if (iText > 0) {
			System.out.println("Search Result is Listed");
		} else {
			System.out.println("No Search Result");
		}

		List<WebElement> oList = driver
				.findElements(By.xpath("//ul[@class='srp-results srp-list clearfix']/li[contains(@class,'s-item s-item__pl-on-bottom')]"));
		System.out.println("Total Value is : " + oList.size());
		for (int row = 1; row < oList.size(); row++) {
			oProduct = oList.get(row);
			// sVal1 will be having Phone Name
			// sVal2 will be having Phone Price
			String sProdName = oProduct.findElement(By.xpath(".//a[@class='s-item__link']"))
					.getText();
			String sProdPrice = oProduct.findElement(By.xpath(".//span[@class='s-item__price']")).getText();
			System.out.println(sProdName);
			System.out.println(sProdPrice);
			//	ScrollPageto(x, y);
			// 1St Param is File Location
			// 2nd Param is Sheet Name Ex : iPhone,Lenovo,Samsung
			// 3rd Param is Row Number
			// 4th Param is Cell Number
			// 5th Param sVal1 orsVal2
			writeCellValueToExcel(sExcelFile, sTxt, row-1, 0, sProdName);
			writeCellValueToExcel(sExcelFile, sTxt, row-1, 1, sProdPrice);
		}
		ScrollPageto(0, 0);

	}

	public static void ScrollPageto(int x, int y) {
		JavascriptExecutor oJs;
		String sCmd;
		oJs = (JavascriptExecutor) driver;
		sCmd = String.format("window.scrollTo(%d,%d)", x, y);
		oJs.executeScript(sCmd);

	}

	public static void writeCellValueToExcel(String sFile, String sSheet, int iRow, int iCell, String sValue) {
		// 1St Param is File Location
		// 2nd Param is Sheet Name Ex : iPhone,Samsung or Nokia
		// 3rd Param is Row Number
		// 4th Param is Cell Number
		// 5th Param sVal1 or sVal2

		InputStream oFile;
		XSSFWorkbook oExcel;
		XSSFSheet oSheet;
		XSSFRow oRow;
		XSSFCell oCell;
		try {
			oFile = new FileInputStream(sFile);
			oExcel = new XSSFWorkbook(oFile);
			oSheet = oExcel.getSheet(sSheet);
			if (oSheet == null) {
				oExcel.createSheet(sSheet);
				oSheet = oExcel.getSheet(sSheet);
			}

			oRow = oSheet.getRow(iRow);
			if (oRow == null) {
				oSheet.createRow(iRow);
				oRow = oSheet.getRow(iRow);
			}

			oCell = oRow.getCell(iCell);

			if (oCell == null) {
				oRow.createCell(iCell);
				oCell = oRow.getCell(iCell);
			}

			oCell.setCellValue(sValue);

			OutputStream oFileWrite = new FileOutputStream(sFile);
			oExcel.write(oFileWrite);
			oFileWrite.flush();
			oFileWrite.close();

			oExcel.close();
			oFile.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
