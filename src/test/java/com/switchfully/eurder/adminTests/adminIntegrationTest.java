package com.switchfully.eurder.adminTests;


import com.switchfully.eurder.service.customers.dto.ReturnCustomerDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class adminIntegrationTest {
    @LocalServerPort
    private int port;

    @Test
    void createAdmin_asAdmin_happyPath() {
        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin@test.com", "password")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .patch("/admin/2")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }
}
