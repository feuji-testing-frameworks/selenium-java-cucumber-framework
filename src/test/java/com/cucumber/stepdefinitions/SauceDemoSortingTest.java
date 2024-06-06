package com.cucumber.stepdefinitions;


import static org.testng.Assert.assertEquals;

import com.cucumber.pages.HomePage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SauceDemoSortingTest {
	HomePage homePage;
	
	@When("User click on sorting")
	public void user_click_on_sorting() throws Exception {
	  homePage = new HomePage(SauceDemoLoginTest.getDriver());
	  homePage.filterProducts("za");
	  Thread.sleep(5000);
	}

	@Then("the products are displayed in the sorting order")
	public void the_products_are_displayed_in_the_sorting_order() {
	   String productText = homePage.getProductText();
	   assertEquals(productText, "Test.allTheThings() T-Shirt (Red)");
	}

}
