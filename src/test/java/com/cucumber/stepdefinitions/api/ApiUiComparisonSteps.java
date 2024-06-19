package com.cucumber.stepdefinitions.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class ApiUiComparisonSteps {
    private List<String> apiHeaders;
    private List<String> uiHeaders;
    private WebDriver driver;

    @Given("I fetch h2 headers from the API")
    public void fetchApiData() {
        String apiUrl = "https://jsonplaceholder.typicode.com/";
        Response response = RestAssured.get(apiUrl);
        if (response.getStatusCode() == 200) {
            String html = response.getBody().asString();
            Document doc = Jsoup.parse(html);
            Elements h2Elements = doc.select("h2");
            apiHeaders = h2Elements.stream().map(Element::text).collect(Collectors.toList());
            System.out.println("API Headers: " + apiHeaders);
        } else {
            throw new RuntimeException("API request failed with status code: " + response.getStatusCode());
        }
    }

    @When("I fetch h2 headers from the UI")
    public void fetchUiData() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://jsonplaceholder.typicode.com/");

        List<WebElement> headerElements = driver.findElements(By.tagName("h2"));
        uiHeaders = headerElements.stream().map(WebElement::getText).collect(Collectors.toList());
        System.out.println("UI Headers: " + uiHeaders);
    }

    @Then("the h2 headers from API and UI should match")
    public void compareApiAndUiData() {
        Assert.assertEquals(uiHeaders, apiHeaders, "Header data does not match between UI and API");
        driver.quit();
    }
}
