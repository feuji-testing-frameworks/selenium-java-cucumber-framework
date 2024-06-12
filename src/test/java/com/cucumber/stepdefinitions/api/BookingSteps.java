package com.cucumber.stepdefinitions.api;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.cucumber.base.Base;
import com.cucumber.pages.api.Booking;
import com.cucumber.pages.api.BookingDates;
import com.cucumber.pages.api.BookingEndpoint;
import com.cucumber.pages.api.CreateToken;
import com.cucumber.testrunner.TestRunner;
import com.github.javafaker.Faker;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class BookingSteps {
	private int bookingId;
	private String token;
	private Response response;
	private CreateToken createToken;
	private Booking booking;
	private Faker faker;

	@Given("I have the user credentials")
	public void i_have_the_user_credentials() {
		faker = new Faker();
		createToken = new CreateToken();
		createToken.setUsername("admin");
		createToken.setPassword("password123");
	}

	@When("I create a token")
	public void i_create_a_token() {
		response = BookingEndpoint.createToken(createToken);
		response.then().log().all();
		token = response.then().statusCode(200).extract().path("token");
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("the token is created successfully")
	public void the_token_is_created_successfully() {
		assertEquals(response.getStatusCode(), 200);
	}

	@Given("I have a booking payload")
	public void i_have_a_booking_payload() {
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

	@When("I create a booking")
	public void i_create_a_booking() {
		response = BookingEndpoint.createBooking(booking);
		bookingId = response.then().log().all().statusCode(200).extract().path("bookingid");
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("the booking is created successfully")
	public void the_booking_is_created_successfully() {
		assertEquals(response.getStatusCode(), 200);
	}

	@Given("I have a booking id")
	public void i_have_a_booking_id() {
		// Assuming bookingId is set from create booking scenario
		assert bookingId > 0;
	}

	@When("I get the booking by id")
	public void i_get_the_booking_by_id() {
		response = BookingEndpoint.getBookingById(bookingId);
		response.then().log().all();
	}

	@Then("the booking is retrieved successfully")
	public void the_booking_is_retrieved_successfully() {
		assertEquals(response.getStatusCode(), 200);
	}

	@Given("I have a booking id and updated booking payload")
	public void i_have_a_booking_id_and_updated_booking_payload() {
		// Assuming bookingId is set from create booking scenario
		assert bookingId > 0;
		booking.setTotalPrice(200);
		booking.setAdditionalNeeds("Dinner");
	}

	@When("I update the booking by id")
	public void i_update_the_booking_by_id() {
		response = BookingEndpoint.updateBookingById(bookingId, booking, token);
		response.then().log().all();
	}

	@Then("the booking is updated successfully")
	public void the_booking_is_updated_successfully() {
		assertEquals(response.getStatusCode(), 200);
	}

	@When("I delete the booking")
	public void i_delete_the_booking() {
		response = BookingEndpoint.deleteBooking(bookingId, token);
		response.then().log().all();
	}

	@Then("the booking is deleted successfully")
	public void the_booking_is_deleted_successfully() {
		assertEquals(response.getStatusCode(), 201);
	}
}
