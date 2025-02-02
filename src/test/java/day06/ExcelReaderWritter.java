package day06;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderWritter {
	
	public static String fileName = "./data/Credo.xlsx";
	public static FileInputStream oFis;
	public static FileOutputStream oFos;
	public static String sheetName = "Course";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//getCellValueBasedOnIndex(1,0);
		//getAllCellValue();
		getAllCellValueAndWriteData();
	}
	
	public static void getCellValueBasedOnIndex(int row,int col) throws Exception {
		// To build the relationship b/w java program and Excel
		oFis = new FileInputStream(fileName);
		/**
		 * When the excel extension is .xlsx then we should use XSSF Pre-fix class from Apache POI Library.
		 * When the excel extension is .xls then we should use HSSF Pre-fix class from Apache POI Library.
		 */
		XSSFWorkbook oWorkBook = new XSSFWorkbook(oFis);
		XSSFSheet oSheet = oWorkBook.getSheet(sheetName);
		XSSFRow oRow = oSheet.getRow(row);
		XSSFCell oCell = oRow.getCell(col);
		String stringCellValue = oCell.getStringCellValue();
		System.out.println("Excel value based on index is : "+stringCellValue);
		oWorkBook.close();
		oFis.close();
	}
	
	
	public static void getAllCellValue() throws Exception {
		// To build the relationship b/w java program and Excel
		oFis = new FileInputStream(fileName);
		XSSFWorkbook oWorkBook = new XSSFWorkbook(oFis);
		XSSFSheet oSheet = oWorkBook.getSheet(sheetName);
		int lastRowNum = oSheet.getLastRowNum();//5
		XSSFRow oRow;
		XSSFCell oCell;
		System.out.println("Last occupied row number is : "+lastRowNum);
		for(int iRow=0;iRow <= lastRowNum;iRow++) {
			oRow = oSheet.getRow(iRow);
			short lastCellNum = oRow.getLastCellNum(); //5
			for(int iCell=0;iCell < lastCellNum;iCell++) {
				oCell = oRow.getCell(iCell);
				CellType cellType = oCell.getCellType();
				switch (cellType) {
				case NUMERIC:
					System.out.print(oCell.getNumericCellValue()+"\t");
					break;
				case STRING:
					System.out.print(oCell.getStringCellValue()+"\t");
					break;
				case BOOLEAN:
					System.out.print(oCell.getBooleanCellValue()+"\t");
					break;
				case ERROR:
					System.out.print("Error in cell value");
					break;
				default:
					System.out.print("Invalid cell data");
					break;
				}
			}
			System.out.println();
		}
		oWorkBook.close();
		oFis.close();
		
	}
	
	public static void getAllCellValueAndWriteData() throws Exception {
		// To build the relationship b/w java program and Excel
		oFis = new FileInputStream(fileName);
		XSSFWorkbook oWorkBook = new XSSFWorkbook(oFis);
		XSSFSheet oSheet = oWorkBook.getSheet(sheetName);
		int lastRowNum = oSheet.getLastRowNum();//5
		XSSFRow oRow;
		XSSFCell oCell;
		System.out.println("Last occupied row number is : "+lastRowNum);
		for(int iRow=1;iRow <= lastRowNum;iRow++) {
			oRow = oSheet.getRow(iRow);
			short lastCellNum = oRow.getLastCellNum(); //5
			for(int iCell=0;iCell < lastCellNum;iCell++) {
				oCell = oRow.getCell(iCell);
				CellType cellType = oCell.getCellType();
				switch (cellType) {
				case NUMERIC:
					System.out.print(oCell.getNumericCellValue()+"\t");
					break;
				case STRING:
					System.out.print(oCell.getStringCellValue()+"\t");
					break;
				case BOOLEAN:
					System.out.print(oCell.getBooleanCellValue()+"\t");
					break;
				case ERROR:
					System.out.print("Error in cell value");
					break;
				default:
					System.out.print("Invalid cell data");
					break;
				}
				if(iCell == 3) {
					oSheet.getRow(iRow).createCell(iCell + 1).setCellValue("Completed");
					oFos = new FileOutputStream(fileName);
					oWorkBook.write(oFos);
					oFos.flush();
				}
			}
			System.out.println();
		}
		oWorkBook.close();
		oFis.close();
		oFos.close();
	}
	
	
}
