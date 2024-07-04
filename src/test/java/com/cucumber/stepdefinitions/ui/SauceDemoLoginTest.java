package com.cucumber.stepdefinitions.ui;

import static org.testng.Assert.assertEquals;
import java.time.Duration;
import com.cucumber.base.Base;
import com.cucumber.pages.ui.AddToCartPage;
import com.cucumber.pages.ui.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SauceDemoLoginTest {
	public LoginPage loginPage;
	public AddToCartPage addToCartPage;

	@Given("User is on the sauce demo login page")
	public void user_is_on_the_sauce_demo_login_page() {
		Base.getDriver().manage().window().maximize();
		Base.getDriver().get("https://www.saucedemo.com/");
		Base.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		Base.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}

	@When("/^User enters the (.*) and (.*)$/")
	public void user_enters_the_username_and_password(String username, String password) {
		loginPage = new LoginPage(Base.getDriver());
		loginPage.enterUsernameAndPassword(username, password);
	}

	@And("Click on login button")
	public void click_on_login_button() {
		loginPage.clickOnLoginButton();
	}

	@Then("User navigates to home page")
	public void user_navigates_to_home_page() throws Exception {
		String headerText = loginPage.getHeaderText();
		Thread.sleep(3000);
		assertEquals(headerText, "Swag Labs");
	}

	@And("Logout from the application")
	public void logout_from_the_application() {
		loginPage.logout();
	}

	@And("browser closes automatically")
	public void browser_closes_automatically() {
		Base.quitDriver();
	}

}