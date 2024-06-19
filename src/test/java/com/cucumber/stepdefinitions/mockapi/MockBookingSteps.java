package com.cucumber.stepdefinitions.mockapi;

import static org.testng.Assert.assertEquals;

import com.cucumber.pages.api.Booking;
import com.cucumber.pages.api.BookingDates;
import com.cucumber.pages.api.BookingEndpoint;
import com.cucumber.pages.api.CreateToken;
import com.github.javafaker.Faker;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class MockBookingSteps {
    private int bookingId;
    private String token;
    private Response response;
    private Faker faker;
    private CreateToken createToken;
    private Booking booking;

    @Given("I have the mock user credentials")
    public void mock_user_credentials() {
    	faker = new Faker();
		createToken = new CreateToken();
		createToken.setUsername("admin");
		createToken.setPassword("password123");
	}

    @When("I create a mock token")
    public void create_mock_token() {
    	response = BookingEndpoint.createToken(createToken);
		response.then().log().all();
		token = response.then().statusCode(200).extract().path("token");
    }

    @Then("the mock token is created successfully")
    public void mock_token_created() {
		assertEquals(response.getStatusCode(), 200);
    }

    @Given("I have a mock booking payload")
    public void mock_booking_payload() {
    	faker = new Faker();
		booking = new Booking();
		booking.setFirstname(faker.name().username());
		booking.setLastname(faker.name().lastName());
		booking.setAdditionalNeeds(faker.random().hex());
		booking.setBookingDates(new BookingDates(faker.date().past(5, java.util.concurrent.TimeUnit.DAYS).toString(),
				faker.date().future(2, java.util.concurrent.TimeUnit.DAYS).toString()));
		booking.setTotalPrice(faker.number().randomDigit());
		booking.setDepositPaid(faker.bool().bool());
    }

    @When("I create a mock booking")
    public void create_mock_booking() {
    	response = BookingEndpoint.createBooking(booking);
		bookingId = response.then().log().all().statusCode(200).extract().path("bookingid");
    }

    @Then("the mock booking is created successfully")
    public void mock_booking_created() {
		assertEquals(response.getStatusCode(), 200);
	}

    @Given("I have a mock booking id")
    public void mock_booking_id() {
    	assert bookingId>0;
    }

    @When("I get the mock booking by id")
    public void get_mock_booking_by_id() {
    	response = BookingEndpoint.getBookingById(bookingId);
		response.then().log().all();
    }

    @Then("the mock booking is retrieved successfully")
    public void mock_booking_retrieved() {
        assertEquals(response.getStatusCode(), 200);
    }

    @Given("I have a mock booking id and updated mock booking payload")
    public void mock_booking_id_and_updated_mock_booking_payload() {
    	faker = new Faker();
		booking = new Booking();
		booking.setFirstname(faker.name().username());
		booking.setLastname(faker.name().lastName());
		booking.setAdditionalNeeds(faker.random().hex());
		booking.setBookingDates(new BookingDates(faker.date().past(5, java.util.concurrent.TimeUnit.DAYS).toString(),
				faker.date().future(2, java.util.concurrent.TimeUnit.DAYS).toString()));
		booking.setTotalPrice(faker.number().randomDigit());
		booking.setDepositPaid(faker.bool().bool());
    }

    @When("I update the mock booking by id")
    public void update_mock_booking() {
    	response = BookingEndpoint.updateBookingById(bookingId, booking, token);
		response.then().log().all();
    }

    @Then("the mock booking is updated successfully")
    public void mock_booking_updated() {
        assertEquals(response.getStatusCode(), 200);
    }

    @Given("I have a mock booking id and patch mock booking payload")
    public void mock_booking_id_and_patch_mock_booking_payload() {
    	faker = new Faker();
		booking = new Booking();
		booking.setFirstname(faker.name().username());
		booking.setLastname(faker.name().lastName());
    }

    @When("I patch the mock booking by id")
    public void patch_mock_booking() {
        response = BookingEndpoint.patchBookingById(bookingId, booking, token);
		response.then().log().all();
    }

    @Then("the mock booking is patched successfully")
    public void mock_booking_patched() {
        assertEquals(response.getStatusCode(), 200);
    }

    @When("I delete the mock booking")
    public void delete_mock_booking() {
    	response = BookingEndpoint.deleteBooking(bookingId, token);
		response.then().log().all();
    }

    @Then("the mock booking is deleted successfully")
    public void mock_booking_deleted() {
        assertEquals(response.getStatusCode(), 201);
    }
}

