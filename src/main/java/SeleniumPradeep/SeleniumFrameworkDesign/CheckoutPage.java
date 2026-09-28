package SeleniumPradeep.SeleniumFrameworkDesign;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FWD.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="#checkout")
	WebElement CheckoutButton;
	
	@FindBy(css="input[id*='first-name']")
	WebElement FirstName;
	
	@FindBy(css="input[id*='last-name']")
	WebElement LastName;
	
	@FindBy(css="input[id*='postal-code']")
	WebElement PostalCode;
	
	@FindBy(css="#continue")
	WebElement ContinueButton;
	
	@FindBy(css=".inventory_item_price")
	List<WebElement> ItemsPrice;
	
	@FindBy(css=".summary_subtotal_label")
	WebElement TotalTag;
	
	@FindBy(css="#finish")
	WebElement FinishButton;
	
	public void enterUserDetails(String firstName, String lastName, String postalCode)
	{
		CheckoutButton.click();
		FirstName.sendKeys(firstName);
		LastName.sendKeys(lastName);
		PostalCode.sendKeys(postalCode);
		ContinueButton.click();
	}
	
	public double getProductsTotal() {
		
		double productsTotal =0;
		for(int i=0; i<ItemsPrice.size(); i++)
		{
			String priceText=ItemsPrice.get(i).getText();
			double price = Double.parseDouble(priceText.replace("$", "").trim());
			productsTotal = productsTotal + price;
		}
	return productsTotal;
	}
	
	public String getTotalValue() {
		String totalTag=TotalTag.getText();
		String totalValue=totalTag.replace("Item total: $", "").trim();
		return totalValue;
	}
	
	public double getWebsiteTotal() {
		 double websiteTotal=Double.parseDouble(getTotalValue());
		 return websiteTotal;
	}
	
	public ConfirmationPage goTo() {
		
		FinishButton.click();
		return new ConfirmationPage(driver);
	}
	
}
