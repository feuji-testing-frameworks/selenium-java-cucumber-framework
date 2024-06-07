package com.cucumber.testrunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",
		glue = {"com.cucumber.stepdefinitions"},
		monochrome = true,
		plugin = {"pretty" , "html:target/Reports/cucumberReport.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // Override the AbstractTestNGCucumberTests class to run scenarios in parallel
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios(){
		return super.scenarios();
	}

}
