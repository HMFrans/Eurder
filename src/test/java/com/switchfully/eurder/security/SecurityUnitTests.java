package com.switchfully.eurder.security;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityUnitTests {
    @LocalServerPort
    private int port;

    @Test
    void given_wrongPassword_throwException() {

        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin", "pasword")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .patch("/admin/member")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    void givenNotExistingUser_throwException() {

        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("NonExistingMember", "password")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .patch("/admin/member")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

}
