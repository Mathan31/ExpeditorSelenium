package wrapper;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadDataFromPropertyFile {
	
	public static String readDataFromPropertyFileBasedOnKey(String fileName,String key) throws IllegalArgumentException {
		String filePath = "./data/"+fileName+".properties";
		File oFile = new File(filePath);
		String proppertyValue = "";
		try {
			FileInputStream fis = new FileInputStream(oFile);
			Properties properties = new Properties();
			properties.load(fis);
			proppertyValue = properties.getProperty(key);
			return proppertyValue;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new IllegalArgumentException("Input is not valid");
	}

}
