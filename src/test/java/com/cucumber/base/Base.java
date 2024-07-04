package com.cucumber.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.cucumber.pages.mockapi.MockServer;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
    private static final Logger logger = LogManager.getLogger(Base.class.getName());
    private static ExtentReports extent;
    private MockServer mockServer;

    private static ThreadLocal<TestContext> testContextThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (testContextThreadLocal.get() == null) {
            testContextThreadLocal.set(new TestContext());
        }
        if (testContextThreadLocal.get().getWebDriver() == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            ChromeDriver driver = new ChromeDriver(chromeOptions);
            testContextThreadLocal.get().setWebDriver(driver);
        }
        return testContextThreadLocal.get().getWebDriver();
    }

    public static void quitDriver() {
        WebDriver driver = testContextThreadLocal.get().getWebDriver();
        if (driver != null) {
            driver.quit();
            testContextThreadLocal.get().setWebDriver(null);
        }
    }

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
        TestContext context = new TestContext();
        ExtentTest test = extent.createTest(scenario.getName(), scenario.getId());
        context.setExtentTest(test);
        testContextThreadLocal.set(context);
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
        ExtentTest test = testContextThreadLocal.get().getExtentTest();
        if (scenario.isFailed()) {
            test.fail(scenario.getStatus().toString());
        } else {
            test.pass(scenario.getStatus().toString());
        }
        extent.flush();
        quitDriver();
    }

    public static ExtentTest getTest() {
        return testContextThreadLocal.get().getExtentTest();
    }
}
