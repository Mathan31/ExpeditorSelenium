package wrapper;

import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

//import ru.yandex.qatools.ashot.AShot;
//import ru.yandex.qatools.ashot.Screenshot;
//import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenShotSession {
	
	public static void takeScreenShotAsFile(WebDriver driver,String fileName) throws Exception {
		File dist = new File("./snap/"+fileName+".png");
		TakesScreenshot shot = (TakesScreenshot)driver;
		File src = shot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, dist);
	}
	
	public static void takeScreenShotAsBase64(WebDriver driver,String fileName) throws Exception {
		File dist = new File("./snap/"+fileName+".png");
		TakesScreenshot shot = (TakesScreenshot)driver;
		String screenshotAsBase64 = shot.getScreenshotAs(OutputType.BASE64);
		File src = OutputType.FILE.convertFromBase64Png(screenshotAsBase64);
		FileUtils.copyFile(src, dist);
	}
	
	public static void takeScreenShotAsByteArray(WebDriver driver,String fileName) throws Exception {
		File dist = new File("./snap/"+fileName+".png");
		TakesScreenshot shot = (TakesScreenshot)driver;
		byte[] screenshotAsByteArray = shot.getScreenshotAs(OutputType.BYTES);
		File src = OutputType.FILE.convertFromPngBytes(screenshotAsByteArray);
		FileUtils.copyFile(src, dist);
	}
	
	public static void takeScreenShotAsFileByPassingWebElement(WebElement element,String fileName) throws Exception {
		File dist = new File("./snap/"+fileName+".png");
		TakesScreenshot shot = (TakesScreenshot)element;
		File src = shot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, dist);
	}

	public static String takeScreenShotAsFileWithDynamicFileName(WebDriver driver,String fileName) throws Exception {
		String fileLocation = System.getProperty("user.dir")+"/snap/"+fileName+System.currentTimeMillis()+".png";
		System.out.println("Screenshot path is : "+fileLocation);
		File dist = new File(fileLocation);
		TakesScreenshot shot = (TakesScreenshot)driver;
		File src = shot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, dist);
		return fileLocation;
	}
	
	public static void takeFullScreenshotAsFile(WebDriver driver,String sFileName) throws Exception {
		Screenshot oShot = new AShot()
							.shootingStrategy(ShootingStrategies.viewportPasting(1000))
							.takeScreenshot(driver);
		String sPath = System.getProperty("user.dir")+"/snap/"+sFileName+System.currentTimeMillis()+".png";
		File oDes = new File(sPath);
		ImageIO.write(oShot.getImage(), "png", oDes);
	}
}
