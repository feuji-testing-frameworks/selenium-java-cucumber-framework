package com.cucumber.stepdefinitions;

import com.cucumber.pages.uipages.SideBarValidation;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SauceDemoSideBarTest {
	SideBarValidation barValidation;
	
	@When("User click on side bar")
	public void user_click_on_side_bar() {
	   barValidation = new SideBarValidation(SauceDemoLoginTest.getDriver());
	   barValidation.clickOnSideBarBtn();
	}

	@And("Check the elements present or not")
	public void check_the_elements_present_or_not() throws Exception {
	    String[] elements = {"All Items","About","Logout","Reset App State"};
	    for(String element : elements) {
	    	Thread.sleep(3000);
	    	barValidation.sideBarValidation(element);
	    }
	}

	@Then("Click on close button")
	public void click_on_close_button() {
	    barValidation.clickOnCloseBtn();
	}

}
