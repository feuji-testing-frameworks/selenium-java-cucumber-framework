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
	public void user_credentials() {
		faker = new Faker();
		createToken = new CreateToken();
		createToken.setUsername("admin");
		createToken.setPassword("password123");
	}

	@When("I create a token")
	public void create_token() {
		response = BookingEndpoint.createToken(createToken);
		response.then().log().all();
		token = response.then().statusCode(200).extract().path("token");
	}

	@Then("the token is created successfully")
	public void token_created() {
		assertEquals(response.getStatusCode(), 200);
	}

	@Given("I have a booking payload")
	public void booking_payload() {
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
	public void create_booking() {
		response = BookingEndpoint.createBooking(booking);
		bookingId = response.then().log().all().statusCode(200).extract().path("bookingid");
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("the booking is created successfully")
	public void booking_created() {
		assertEquals(response.getStatusCode(), 200);
	}

	@Given("I have a booking id")
	public void booking_id() {
	     assert bookingId>0;
	}

	@When("I get the booking by id")
	public void get_booking_by_id() {
		response = BookingEndpoint.getBookingById(bookingId);
		response.then().log().all();
	}

	@Then("the booking is retrieved successfully")
	public void booking_retrieved() {
		assertEquals(response.getStatusCode(), 200);
	}

	@Given("I have a booking id and updated booking payload")
	public void booking_id_and_updated_booking_payload() {
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

	@When("I update the booking by id")
	public void update_booking() {
		response = BookingEndpoint.updateBookingById(bookingId, booking, token);
		response.then().log().all();
	}

	@Then("the booking is updated successfully")
	public void booking_updated() {
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Given("I have a booking id and patch booking payload")
	public void booking_id_and_patched_booking_payload() {
		faker = new Faker();
		booking = new Booking();
		booking.setFirstname(faker.name().username());
		booking.setLastname(faker.name().lastName());
	}

	@When("I patch the booking by id")
	public void patch_booking() {
		response = BookingEndpoint.patchBookingById(bookingId, booking, token);
		response.then().log().all();
	}

	@Then("the booking is patched successfully")
	public void booking_patched() {
		assertEquals(response.getStatusCode(), 200);
	}

	@When("I delete the booking")
	public void delete_booking() {
		response = BookingEndpoint.deleteBooking(bookingId, token);
		response.then().log().all();
	}

	@Then("the booking is deleted successfully")
	public void booking_deleted() {
		assertEquals(response.getStatusCode(), 201);
	}
}
