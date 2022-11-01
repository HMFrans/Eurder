package com.switchfully.eurder.integrationTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderIntegrationTest {
    @LocalServerPort
    private int port;

    @Test
    void createOrderByMember_HappyPath() {
        String body = "[{\"name\":\"flour\",\"amount\":2},{\"name\":\"egg\",\"amount\":100}]";

        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("member", "password")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .body(body)
                .contentType(ContentType.JSON)
                .post("/order")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void createOrderByNonLoggedInUser_Unauthorized() {
        String body = "[{\"name\":\"flour\",\"amount\":2},{\"name\":\"egg\",\"amount\":100}]";

        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("notAMember", "password")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .body(body)
                .contentType(ContentType.JSON)
                .post("/order")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    void createOrderWithoutItems_Exception() {
        String body = "[]";

        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("member", "password")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .body(body)
                .contentType(ContentType.JSON)
                .post("/order")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }


}
