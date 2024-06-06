package com.cucumber.stepdefinitions;

import static org.testng.Assert.assertTrue;

import com.cucumber.pages.AddToCartPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SauceDemoCheckoutTest {
	public AddToCartPage addToCartPage;

	@When("User add the product to the cart")
	public void user_add_the_product_to_the_cart() {
	   addToCartPage = new AddToCartPage(SauceDemoLoginTest.getDriver());
	   addToCartPage.clickOnAddToCart();
	}

	@And("Click on the checkout")
	public void click_on_the_checkout() {
	    addToCartPage.clickOnCheckout();
	}

	@Then("Enter the details")
	public void enter_the_details() throws Exception {
	   addToCartPage.enterUserDetails("Harry", "Potter", "533212");
	   Thread.sleep(3000);
	}

	@And("Click on finish")
	public void click_on_finish() {
	    addToCartPage.clickOnFinish();
	}

	@Then("User see the Back to Home Btn")
	public void user_see_the_back_to_home_btn() {
	   assertTrue(addToCartPage.isBackHomeBtnVisible());
	}

	@And("Click the Back to Home Btn")
	public void click_the_back_to_home_btn() {
		addToCartPage.clickOnBackHome();
	}
}
