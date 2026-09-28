package SeleniumPradeep.SeleniumFrameworkDesign;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FWD.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products =driver.findElements(By.cssSelector(".inventory_item"));
//	driver.findElement(By.cssSelector(".shopping_cart_link")).click();
	
//	cssSelector(".btn")
	
	
	@FindBy(css=".inventory_item")
	List<WebElement> products;
	
	@FindBy(css=".shopping_cart_link")
	WebElement CartButton;
	
	@FindBy(css=".inventory_item_name")
	List<WebElement> cartItems;
	
	By button = By.cssSelector(".btn");
	By itemName =By.cssSelector(".inventory_item_name");

	
	public List<WebElement> getProductList() {
		return products;
	}
	
	public List<String> ProductList()
	{
		List<String> productList=Arrays.asList("Sauce Labs Bike Light","Sauce Labs Backpack", "Sauce Labs Onesie");
		return productList;
	}
	public void addToCart(String ProductName) {
		products.stream()
      .filter(product -> product.findElement(
    		  itemName)
              .getText()
              .equals(ProductName))
      .findFirst()
      .ifPresent(product ->
              product.findElement(button).click());

	}
	
	public CheckoutPage goToCart() {
		
		CartButton.click();
		return new CheckoutPage(driver);
	}
	
	public List<String> getCartList() {
		
	    return cartItems.stream()
	            .map(element -> element.getText())
	            .collect(Collectors.toList());
	}
	
	}
	

