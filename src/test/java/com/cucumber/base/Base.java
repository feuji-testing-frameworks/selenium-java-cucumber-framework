//package com.cucumber.base;
//
//import java.time.Duration;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import io.cucumber.java.AfterAll;
//import io.cucumber.java.BeforeAll;
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class Base {
//	
//	public static WebDriver driver;
//	
//	@BeforeAll
//	public static void setup() {
//		driver = new ChromeDriver();
//	    WebDriverManager.chromedriver().setup();
//	    driver.manage().window().maximize();
//	    driver.get("https://www.saucedemo.com/");
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
//	}
//	
//	@AfterAll
//	public static void teardown() {
//		if(driver!=null) {
//			driver.quit();
//		}
//	}
//
//}
