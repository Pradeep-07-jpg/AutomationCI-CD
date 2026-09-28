//package SeleniumPradeep.SeleniumFrameworkDesign;
//
//
//
//	public class AddtoCartDuplicate extends BaseTest {
//		package SeleniumPradeep.SeleniumFrameworkDesign;
//
//		import static org.testng.Assert.assertEquals;
//		import static org.testng.Assert.assertTrue;
//
//		import java.util.Arrays;
//		import java.util.List;
//		import java.util.stream.Collectors;
//
//		import org.openqa.selenium.By;
//		import org.openqa.selenium.WebElement;
//		import org.openqa.selenium.chrome.ChromeOptions;
//		import org.testng.Assert;
//		import org.testng.annotations.Test;
//		
//		@Test
//		public void addtoCart()
//		{
//			String text=driver.findElement(By.id("login_credentials")).getText();
//			String[] users=text.split("\\r?\\n");
//			String username=users[1];
//			String text2=driver.findElement(By.cssSelector("div[class='login_password']")).getText();
//			String[] pass=text2.split("\\r?\\n");
//			String password=pass[1];
////			List<String> productList=Arrays.asList("Sauce Labs Bike Light","Sauce Labs Backpack", "Sauce Labs Onesie");
//			driver.findElement(By.id("user-name")).sendKeys(username);
//			driver.findElement(By.id("password")).sendKeys(password);
//			driver.findElement(By.cssSelector("input[class='submit-button btn_action']")).click();
//			
//			//AddtoCart
//			CartPage cartPage = new CartPage(driver);
//			List<WebElement> products =cartPage.getProductList();
//			List<String>productList=cartPage.ProductList();
//			productList.forEach(name-> 
//			{
//				products.stream()
//	            .filter(product -> product.findElement(
//	                    By.cssSelector(".inventory_item_name"))
//	                    .getText()
//	                    .equals(name))
//	            .findFirst()
//	            .ifPresent(product ->
//	                    product.findElement(By.cssSelector(".btn")).click());
//	});
//			//Cross-Validation with Cart
//			driver.findElement(By.cssSelector(".shopping_cart_link")).click();
//			List<String> cartList=driver.findElements(By.cssSelector(".inventory_item_name")).stream().map(element -> element.getText()).collect(Collectors.toList());
//			Assert.assertEquals(cartList, productList);
//			
//			//Personal Details Section
//			driver.findElement(By.cssSelector("#checkout")).click();
//			driver.findElement(By.cssSelector("input[id*='first-name']")).sendKeys("Surender");
//			driver.findElement(By.cssSelector("input[id*='last-name']")).sendKeys("A");
//			driver.findElement(By.cssSelector("input[id*='postal-code']")).sendKeys("800095");
//			driver.findElement(By.cssSelector("#continue")).click();
//			
//			//Cross Verify the price total with items Website Total
//			List<WebElement> itemsPrice=driver.findElements(By.cssSelector(".inventory_item_price"));
//			double productsTotal =0;
//			for(int i=0; i<itemsPrice.size(); i++)
//			{
//				String priceText=itemsPrice.get(i).getText();
//				double price = Double.parseDouble(priceText.replace("$", "").trim());
//				productsTotal = productsTotal + price;
//			}
//			
//			System.out.println("Products Total: $" + productsTotal);
//			
//			String totalTag=driver.findElement(By.cssSelector(".summary_subtotal_label")).getText();
//			String totalValue=totalTag.replace("Item total: $", "").trim();
//			double websiteTotal=Double.parseDouble(totalValue);
//			System.out.println("Website Total: $" +websiteTotal);
//			Boolean result = productsTotal == websiteTotal;
//			Assert.assertEquals(productsTotal, websiteTotal);
//			Assert.assertTrue(result);
//			
//			
//			//Confirm the order through printing the greeting message
//			driver.findElement(By.cssSelector("#finish")).click();
//			String cnfMessage=driver.findElement(By.cssSelector(".complete-header")).getText();
//			Assert.assertTrue(cnfMessage.equalsIgnoreCase("Thank you for your order!"));
//			System.out.println(cnfMessage);
//			}
//
//		}
//
//
//
//
//
//}
