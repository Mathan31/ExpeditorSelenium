package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class PreAndPostCondition {
	
	@Before
	public void beforeScenario() {
		System.out.println("It will be getting executed before each scenarios from all the feature file");
	}
	
	@After
	public void afterScenario() {
		System.out.println("It will be getting executed after each scenarios from all the feature file");
	}
}
