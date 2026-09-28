package SeleniumPradeep.SeleniumFrameworkDesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FWD.TestComponents.BaseTest;

public class AddtoCart extends BaseTest {
	
	@Test
	public void addtoCart() throws IOException
	{
		LandingPage landingPage = lanuchApplication();
//		String userName=landingPage.getUsername();
//		String Password=landingPage.getPassword();
		CartPage cartPage = landingPage.userDetails(landingPage.getUsername(), landingPage.getPassword());
//		CartPage cartPage =landingPage.userDetails(userName, Password);
		
		//AddtoCart
		List<WebElement> products =cartPage.getProductList();
		List<String>productList=cartPage.ProductList();
		productList.forEach(name->cartPage.addToCart(name));
		CheckoutPage checkoutPage =cartPage.goToCart();
		
		//Cross-Validation with Cart		
		List<String> cartList=cartPage.getCartList();
		Assert.assertEquals(cartList, productList);
		
		//Personal Details Section
		checkoutPage.enterUserDetails("Surender", "A", "600094");
		
		//Cross Verify the price total with items Website Total
		double productsTotal=checkoutPage.getProductsTotal();
		double websiteTotal=checkoutPage.getWebsiteTotal();
		System.out.println("Products Total: $" + productsTotal);
		System.out.println("Website Total: $" +websiteTotal);
//		Boolean result = productsTotal == websiteTotal;
		Assert.assertEquals(productsTotal, websiteTotal);
//		Assert.assertTrue(result);
		ConfirmationPage confirmationPage = checkoutPage.goTo();
		
		
		//Confirm the order through printing the greeting message
		String CNFMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(CNFMessage.equalsIgnoreCase("Thank you for your order!"));
		System.out.println(CNFMessage);
		}

//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[][] {{"visual_user","secret_sauce", Arrays.asList("Test.allTheThings() T-Shirt (Red)","Sauce Labs Onesie")},
//			{"standard_user","secret_sauce",Arrays.asList("Sauce Labs Backpack","Sauce Labs Bolt T-Shirt")}};
//	}
	}



