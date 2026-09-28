package FWD.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import io.cucumber.java.Before;

import FWD.TestComponents.BaseTest;
import SeleniumPradeep.SeleniumFrameworkDesign.CartPage;
import SeleniumPradeep.SeleniumFrameworkDesign.CheckoutPage;
import SeleniumPradeep.SeleniumFrameworkDesign.ConfirmationPage;
import SeleniumPradeep.SeleniumFrameworkDesign.LandingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionsImp extends BaseTest {
	@Before
	public void setup() throws IOException {
	    InitializeDriver();
	}
	
	public LandingPage landingPage;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;
	@Given("I landed on E-commerce page")
	public void I_landed_on_ecommerce_page() throws IOException
	{
		landingPage =lanuchApplication();
	}

	@Given("^logged in with (.+) and enter the password as (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		cartPage = landingPage.userDetails(username, password);
	}
	
//	When I add the product  <productName> to Cart
	@When("^I add the product  (.+) to Cart$")
	public void I_add_product_to_cart(String productName) {
		List<WebElement> products =cartPage.getProductList();
		cartPage.addToCart(productName);
	}
	
	//And checkout the product with <productName> and click submit order
	@And ("^checkout the product with (.+) and click submit order$")
	public void checkout_product_with_productName_and_click_submit(String productName) {
		checkoutPage =cartPage.goToCart();
		List<String> cartList=cartPage.getCartList();
		Assert.assertTrue(cartList.contains(productName));
	}
	
	@Then("^Enter personal details (.+) and (.+) with (.+) after clicking the submit button$")
	public void enter_personal_details(String Firstname, String Secondname, String Pincode) {
		checkoutPage.enterUserDetails(Firstname, Secondname, Pincode);
		confirmationPage = checkoutPage.goTo();
	}
}
