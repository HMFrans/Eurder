package com.switchfully.eurder.customerTests;

import com.switchfully.eurder.service.customers.dto.NewCustomerDto;
import com.switchfully.eurder.service.customers.dto.ReturnCustomerDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CustomerIntegrationTest {

    @LocalServerPort
    private int port;

    @Test
    void addMember_happyPath() {

        NewCustomerDto body = new NewCustomerDto(
                "Spongebob",
                "Squarepants",
                "Squarepants@hotmail.com",
                "BikiniBottom",
                "1000",
                "waterstreet",
                "1",
                "string",
                "55/55555",
                "password"
        );

        ReturnCustomerDto result = RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .body(body)
                .contentType(ContentType.JSON)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(ReturnCustomerDto.class);

        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result.getEmailAddress()).isEqualTo("Squarepants@hotmail.com");
    }

    @Test
    void addMember_emailAlreadyInUse_throwException() {
        NewCustomerDto body = new NewCustomerDto(
                "Spongebob",
                "Squarepants",
                "customer@test.com",
                "BikiniBottom",
                "1000",
                "waterstreet",
                "1",
                "string",
                "55/55555",
                "password"
        );
        RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .body(body)
                .contentType(ContentType.JSON)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void getAllCustomersAsAdmin_happyPath() {
        ReturnCustomerDto[] returnCustomerDtos = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin@test.com", "password")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ReturnCustomerDto[].class);

        ReturnCustomerDto result = Arrays.stream(returnCustomerDtos).findFirst().orElseThrow();
        Assertions.assertThat(result.getId()).isNotNull();
        assertThat(result.getFirstName()).isEqualTo("admin");
        assertThat(result.getEmailAddress()).isEqualTo("admin@test.com");
    }

    @Test
    void getACustomersAsAdmin_happyPath() {
        ReturnCustomerDto result = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin@test.com", "password")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get("/customers/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ReturnCustomerDto.class);

        Assertions.assertThat(result.getId()).isNotNull();
        assertThat(result.getFirstName()).isEqualTo("admin");
        assertThat(result.getEmailAddress()).isEqualTo("admin@test.com");
    }

    @Test
    void getACustomersAsCustomer_throwsException() {
        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("customer@test.com", "password")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get("/customers/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    void getACustomersAsManager_wrongPassword_throwsException() {
        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin@test.com", "wrong")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get("/customers/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    void getACustomerwithNonExistingAdmin_throwsException() {
        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("custoeemer@test.com", "password")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get("/customers/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }



}
