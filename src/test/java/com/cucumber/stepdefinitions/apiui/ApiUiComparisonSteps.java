package com.cucumber.stepdefinitions.apiui;

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
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.cucumber.base.Base;
import com.cucumber.pages.apiui.ApiUi;
import java.util.List;
import java.util.stream.Collectors;

public class ApiUiComparisonSteps {
	
    private ApiUi apiUi;

    @Given("I fetch h2 headers from the API")
    public void fetchApiData() {
        String apiUrl = "https://jsonplaceholder.typicode.com/";
        Response response = RestAssured.get(apiUrl);
        if (response.getStatusCode() == 200) {
            String html = response.getBody().asString();
            Document doc = Jsoup.parse(html);
            Elements h2Elements = doc.select("h2");
            apiUi = new ApiUi();
            apiUi.setApiHeaders(h2Elements.stream().map(Element::text).collect(Collectors.toList()));        
        } else {
            throw new RuntimeException("API request failed with status code: " + response.getStatusCode());
        }
    }

    @When("I fetch h2 headers from the UI")
    public void fetchUiData() {
        Base.getDriver().get("https://jsonplaceholder.typicode.com/");
        List<WebElement> headerElements = Base.getDriver().findElements(By.tagName("h2"));
        apiUi.setUiHeaders(headerElements.stream().map(WebElement::getText).collect(Collectors.toList())); 
    }

    @Then("the h2 headers from API and UI should match")
    public void compareApiAndUiData() {
        Assert.assertEquals(apiUi.getUiHeaders(), apiUi.getApiHeaders(), "Header data does not match between UI and API");
        Base.getDriver().quit();
    }
}
