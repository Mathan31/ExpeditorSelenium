package wrapper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataProviderObject{

	public static Object[][] getValue(String sExcelName) throws Exception {
		
		Object[][] data = null ;

			String fileLocation = "./data/"+sExcelName+".xlsx";
			FileInputStream fis = new FileInputStream(fileLocation);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);	

			// get the number of rows 3
			int rowCount = sheet.getLastRowNum();

			// get the number of columns 2
			int columnCount = sheet.getRow(0).getLastCellNum();
			data = new Object[rowCount][columnCount];

			// loop through the rows
			for(int i=1; i <=rowCount; i++){
				
					XSSFRow row = sheet.getRow(i);
					for(int j=0; j <columnCount; j++){ // loop through the columns
						
							Object cellValue = null;
							
								XSSFCell cell = row.getCell(j);
								CellType cellType = cell.getCellType();
								switch (cellType) {
								case NUMERIC:
									long numeric = (long)cell.getNumericCellValue();
									String valueOfNumeric = String.valueOf(numeric);
									cellValue = valueOfNumeric;
									break;
									
								case STRING:
									cellValue =cell.getStringCellValue();
									break;

								case BOOLEAN:
									System.out.print(cell.getBooleanCellValue()+"\t");
									break;
								case ERROR:
									System.out.print("It is an Empty Row!!!");
									break;
								default:
									System.out.print("Error in Reading the Data ");
									break;
								}
							data[i-1][j]  = cellValue; // add to the data array data[0][0] = iPhone,data[0][1] = Cell
										
					}

				 
			}
			fis.close();
			workbook.close();
		return data;

	}
}
