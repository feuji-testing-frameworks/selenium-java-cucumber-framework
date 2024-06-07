package com.cucumber.stepdefinitions.ui;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.cucumber.pages.ui.AddToCartPage;
import com.cucumber.pages.ui.LoginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SauceDemoLoginTest {
	public LoginPage loginPage;
	public static WebDriver driver;
	public AddToCartPage addToCartPage;
	
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (webDriverThreadLocal.get() == null) {
            // Initialize WebDriver for the current thread
            WebDriver driver = new ChromeDriver();
            webDriverThreadLocal.set(driver);
        }
        return webDriverThreadLocal.get();
    }

    public static void quitDriver() {
        WebDriver driver = webDriverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            webDriverThreadLocal.remove();
        }
    }
	
	@Given("User is on the sauce demo login page")
	public void user_is_on_the_sauce_demo_login_page() {
	    getDriver().manage().window().maximize();
	    getDriver().get("https://www.saucedemo.com/");
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}

	@When("/^User enters the (.*) and (.*)$/")
	public void user_enters_the_username_and_password(String username,String password) {
	    loginPage = new LoginPage(getDriver());
	    loginPage.enterUsernameAndPassword(username,password);
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
//	   driver.quit();
		quitDriver();
	}

}
