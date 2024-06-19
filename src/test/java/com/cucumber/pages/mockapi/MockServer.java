package com.cucumber.pages.mockapi;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class MockServer {

    private WireMockServer wireMockServer;

    public void startServer() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        wireMockServer.start();

        // Mock endpoint for token generation
        wireMockServer.stubFor(post(urlEqualTo("/mockauth"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"token\": \"mock_dummy_token\" }")));

        // Mock endpoint for getting all mock booking IDs
//        wireMockServer.stubFor(get(urlEqualTo("/mockbooking"))
//                .willReturn(aResponse()
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("[{\"bookingid\": 1}, {\"bookingid\": 2}, {\"bookingid\": 3}]")));

        // Mock endpoint for creating a mock booking
        wireMockServer.stubFor(post(urlEqualTo("/mockbooking"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"bookingid\": 233}")));

        // Mock endpoint for getting mock booking details based on ID
        wireMockServer.stubFor(get(urlPathMatching("/mockbooking/\\d+"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"bookingid\":223, \"firstname\": \"Harry\" ,"
                                + "\"lastname\": \"Potter\"}")));

        // Mock endpoint for updating the mock booking details based on ID
        wireMockServer.stubFor(put(urlPathMatching("/mockbooking/\\d+"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"bookingid\":223, \"firstname\": \"John\" ,"
                                + "\"lastname\": \"Wick\"}")));

        // Mock endpoint for patching the mock booking details based on ID
        wireMockServer.stubFor(patch(urlPathMatching("/mockbooking/\\d+"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"bookingid\":223, \"firstname\": \"UpdatedName\" ,"
                                + "\"lastname\": \"UpdatedLastName\"}")));

        // Mock endpoint for deleting the mock booking details based on ID
        wireMockServer.stubFor(delete(urlPathMatching("/mockbooking/\\d+"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\":\"Deleted the mock booking details successfully\"}")));
    }

    public void stopServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
    
    public int getPort() {
    	return wireMockServer.port();
    }
}
