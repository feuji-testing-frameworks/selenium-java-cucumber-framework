package com.cucumber.pages.api;

public class Routes {
	public static String booking_base_url = "https://restful-booker.herokuapp.com";
	public static String auth_post_url = booking_base_url + "/auth";
	public static String booking_post_url = booking_base_url + "/booking";
	public static String booking_get_url = booking_base_url + "/booking/{id}";
	public static String booking_update_url = booking_base_url + "/booking/{id}";
	public static String booking_delete_url = booking_base_url + "/booking/{id}";
}

