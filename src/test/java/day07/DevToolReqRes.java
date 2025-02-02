package day07;

import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v129.network.Network;
import org.openqa.selenium.devtools.v129.network.model.Request;
import org.openqa.selenium.devtools.v129.network.model.Response;

public class DevToolReqRes {

	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		devTools.addListener(Network.requestWillBeSent(), reqConsume -> {
			Request request = reqConsume.getRequest();
			String url = request.getUrl();
			System.out.println(url);
		});
		
		devTools.addListener(Network.responseReceived(), resConsume -> {
			Response response = resConsume.getResponse();
			System.out.println("Response code : "+response.getStatus());
			System.out.println("Response Text : "+response.getStatusText());
		});
		
		driver.get("https://weatherstack.com/");
		driver.quit();
	}

}
