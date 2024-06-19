package com.cucumber.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.cucumber.pages.api.Booking;
import com.cucumber.pages.api.BookingDates;
import com.cucumber.pages.api.CreateToken;
import com.cucumber.pages.mockapi.MockServer;
import com.github.javafaker.Faker;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;

public class Base{
    private static final Logger logger = LogManager.getLogger(Base.class.getName());
    //public static ExtentReports extent;
    public static ExtentTest test;
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private MockServer mockServer;
  

    @Before
    public void beforeScenario(Scenario scenario) {
        if (extent == null) {
            logger.info("Report Setup in each Test");
            ExtentSparkReporter spark = new ExtentSparkReporter("./reports/Extentreport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("MyReport");
            spark.config().setReportName("Test Report");
            spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        }
        ExtentTest test = extent.createTest(scenario.getName(), scenario.getId());
        extentTest.set(test);
    }

    @Before("@MockFeature")
    public void beforeScenario() {
        mockServer = new MockServer();
        mockServer.startServer();
        System.out.println(mockServer.getPort());
    }

    @After("@MockFeature")
    public void afterScenario() {
        mockServer.stopServer();
    }

    @After
    public void afterScenario(Scenario scenario) {
        ExtentTest test = extentTest.get();
        if (scenario.isFailed()) {
            test.fail(scenario.getStatus().toString());
        } else {
            test.pass(scenario.getStatus().toString());
        }
        extent.flush();
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }
}
