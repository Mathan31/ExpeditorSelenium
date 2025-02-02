package day06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ReadingDataCSV {

	public static void main(String[] args) throws Exception {
		readDataFromCSVUsingFileReader();
		System.out.println("**************************");
		readDataFromCSVUsingScanner();
	}
	
	/**
	 * Java can read or write the data in 2 forms
	 * 	- Character Stream
	 *  - Byte Stream
	 */
	public static void readDataFromCSVUsingFileReader() {
		String filePath = "./data/CredoCourses.csv";
		FileReader oRead;
		BufferedReader bfRead = null;
		try {
			oRead = new FileReader(filePath);
			bfRead = new BufferedReader(oRead);
			String readLine = bfRead.readLine();
			while(readLine!=null) {
				String[] splitData = readLine.split(",");
				for (String data : splitData) {
					System.out.print(data+"\t");
				}
				readLine = bfRead.readLine();
				System.out.println();
			}
			bfRead.close();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void readDataFromCSVUsingScanner() throws Exception {
		String filePath = "./data/CredoCourses.csv";
		File oFile = new File(filePath);
		Scanner scanner = new Scanner(oFile);
		scanner.useDelimiter(",");
		while(scanner.hasNext()) {
			System.out.print(scanner.next()+"\t");
		}
		scanner.close();
		
	}

}
