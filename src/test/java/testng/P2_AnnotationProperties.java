package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class P2_AnnotationProperties {
	
  @Test(priority = 1)
  public void testCase1() {
	  System.out.println("Test Case 1");
  }
  
  @Test(priority = 2,enabled = true,invocationCount = 5)
  public void testCase2() {
	  System.out.println("Test Case 2");
  }
  
  @Test(priority = 3)
  public void aestCase3() {
	  System.out.println("Test Case 3");
  }
  
    
  
  
}
