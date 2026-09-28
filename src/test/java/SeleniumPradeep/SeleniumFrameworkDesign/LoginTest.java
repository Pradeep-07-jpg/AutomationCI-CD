package SeleniumPradeep.SeleniumFrameworkDesign;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import FWD.TestComponents.Retry;

import FWD.TestComponents.BaseTest;
import SeleniumPradeep.SeleniumFrameworkDesign.LandingPage;

public class LoginTest extends BaseTest{
	
	@Test(groups= {"ValidTestOnly"})
	public void ValidLogin() throws IOException
	{
		LandingPage landingPage = lanuchApplication();
		String username=landingPage.getUsername();
		String password=landingPage.getPassword();
		landingPage.userDetails(username, password);
	}
	
	
	
	@Test(dependsOnMethods= {"ValidLogin"},retryAnalyzer=Retry.class)
	public void InvalidLogin() throws IOException
	{
		LandingPage landingPage1 = lanuchApplication();
		String userName1=landingPage1.getUsername();
		landingPage1.userDetails(userName1, "secretreceipe");
		String errorText=driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
		System.out.println(errorText);
		Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", errorText);
		
	}

}
