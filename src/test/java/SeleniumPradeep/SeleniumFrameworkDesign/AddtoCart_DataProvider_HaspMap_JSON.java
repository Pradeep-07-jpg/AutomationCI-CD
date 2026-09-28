package SeleniumPradeep.SeleniumFrameworkDesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FWD.TestComponents.BaseTest;

public class AddtoCart_DataProvider_HaspMap_JSON extends BaseTest {
	
	@Test (dataProvider="getData", groups= {"Purchase"})
	public void addtoCart(HashMap<String,Object> input) throws IOException
	{
		LandingPage landingPage = lanuchApplication();
		CartPage cartPage = landingPage.userDetails((String) input.get("email"), (String) input.get("password"));

		
		//AddtoCart
		List<WebElement> products =cartPage.getProductList();
		List<String>productList= (List<String>) input.get("Products");
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
		System.out.println("EMAIL FROM JSON = " + input.get("email"));
		}

	@DataProvider
	public Object[][] getData() throws IOException
	{
	
		List<HashMap<String,Object>> data =getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\FWD\\Data_JSON\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	
	
	
	
//		DataProvider Basic & Simple Method (First)
	
//		return new Object[][] {{"visual_user","secret_sauce", Arrays.asList("Test.allTheThings() T-Shirt (Red)","Sauce Labs Onesie")},
//			{"standard_user","secret_sauce",Arrays.asList("Sauce Labs Backpack","Sauce Labs Bolt T-Shirt")}};
	
// 		DataProvider Using HashMap Method (Second)
	
//		HashMap<String,Object> map = new HashMap<String,Object>();
//		map.put("email", "visual_user");
//		map.put("password", "secret_sauce");
//		List<String> productsList =Arrays.asList("Test.allTheThings() T-Shirt (Red)","Sauce Labs Onesie");
//		map.put("Products", productsList);
//	
//		HashMap<String,Object> map1 = new HashMap<String,Object>();
//		map1.put("email", "standard_user");
//		map1.put("password", "secret_sauce");
//		List<String> productsList1 =Arrays.asList("Sauce Labs Backpack","Sauce Labs Bolt T-Shirt");
//		map1.put("Products", productsList1);
	}
	}



