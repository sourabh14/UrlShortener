package com.example.UrlShortener.url.service.impl;

import com.example.UrlShortener.url.dto.CreateShortUrlResponse;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class UrlServiceImplTest {
    @BeforeAll
    static void setup() {
        // Set base URI and port
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(8081)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    void testCreateShortUrl() {
        String requestBody = "{ \"url\": \"http://www.google.com\" }";

        CreateShortUrlResponse response = RestAssured.given()
                .body(requestBody)
                .when()
                .post("/api/v1/url/create")
                .then()
                .statusCode(200)
                .extract()
                .as(CreateShortUrlResponse.class);

        // Verify the response
        assert response.getShortenedUrl() != null;
        assert response.getUrl().equals("http://www.google.com");
    }

    @Test
    void testRedirectUrl() {
        String requestBody = "{ \"url\": \"http://www.google.com\" }";

        CreateShortUrlResponse response = RestAssured.given()
                .body(requestBody)
                .when()
                .post("/api/v1/url/create")
                .then()
                .statusCode(200)
                .extract()
                .as(CreateShortUrlResponse.class);

        System.out.println("shortUrl: " + response.getShortenedUrl());
        String shortUrlKey = response.getShortenedUrl().substring(response.getShortenedUrl().lastIndexOf("/") + 1); // Replace with a valid key
        System.out.println("shortUrlKey: " + shortUrlKey);

        Response response1 = RestAssured.given()
                .when()
                .get("/" + shortUrlKey); // Replace with the actual long URL
        System.out.println("response header: " + response1.getHeaders());
        System.out.println("response body: " + response1.getBody().toString());
    }

}