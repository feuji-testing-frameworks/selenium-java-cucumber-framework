package com.cucumber.base;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class TestContext {
    private WebDriver webDriver;
    private ExtentTest extentTest;

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public ExtentTest getExtentTest() {
        return extentTest;
    }

    public void setExtentTest(ExtentTest extentTest) {
        this.extentTest = extentTest;
    }
}

