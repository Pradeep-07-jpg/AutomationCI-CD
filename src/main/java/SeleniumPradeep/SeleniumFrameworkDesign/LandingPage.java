package SeleniumPradeep.SeleniumFrameworkDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FWD.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {

	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//String text2=driver.findElement(By.cssSelector("div[class='login_password']")).getText();
	@FindBy(id="login_credentials")
	WebElement userEmail;
	
	@FindBy(css="div[class='login_password']")
	WebElement userPassword;
	
	@FindBy(id="user-name")
	WebElement usernameEntry;
	
	@FindBy(id="password")
	WebElement passwordentry;
	
	@FindBy(css="input[class='submit-button btn_action']")
	WebElement submit;
	
	public String getUsername() {
		
		String text=userEmail.getText();
		String[] users=text.split("\\r?\\n");
		String username=users[1];
		return username;
	}
	
	public String getPassword() {
		
		String text2=userPassword.getText();
		String[] pass=text2.split("\\r?\\n");
		String password=pass[1];
		return password;
	}
	
	public CartPage userDetails(String username, String password) {
		usernameEntry.sendKeys(username);
		passwordentry.sendKeys(password);
		submit.click();
		return new CartPage(driver);
	}
	
	public void goTO() {
		driver.get("https://www.saucedemo.com/");
		
	}

	// This below method is especially created for HashMap (DataProvider)
//	public CartPage userDetails1(Object object, Object object2) {
//		usernameEntry.sendKeys(getUsername());
//		passwordentry.sendKeys(getPassword());
//		submit.click();
//		return new CartPage(driver);
//	}
	
	
}
