package com.switchfully.eurder.integrationTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberIntegrationTests {

    @LocalServerPort
    private int port;

    @Test
    void AddNewMember_HappyPath() {

        String body = "{\"firstName\":\"Spongebob\",\"lastName\":\"Squarepants\",\"emailAddress\":\"Squarepants@hotmail.com\",\"city\":\"BikiniBottom\",\"postalCode\":\"1000\",\"streetName\":\"waterstreet\",\"houseNumber\":\"1\",\"additionalInfo\":\"string\",\"phoneNumber\":\"string\",\"password\":\"string\"}";

        RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .body(body)
                .contentType(ContentType.JSON)
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

    }

    @Test
    void makeAdmin_HappyPath() {
        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin", "password")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .post("/admin/memberToAdmin")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void getAllMembers_HappyPath() {
        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin", "password")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void getMember_happyPath() {
        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin", "password")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get("/members/admin")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void getMember_NoSuchMember() {
        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin", "password")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get("/members/adminaaaaa")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }




}
