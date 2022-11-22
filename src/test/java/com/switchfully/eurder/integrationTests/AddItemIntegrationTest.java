package com.switchfully.eurder.integrationTests;

import com.switchfully.eurder.service.items.dto.AddItemDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddItemIntegrationTest {

    @LocalServerPort
    private int port;

    @Test
    void addNewItemByAdmin_HappyPath() {
        String body = "{\"name\":\"flour\",\"description\":\"crushedgrain\",\"price\":\"15.00\",\"amount\":1}";

        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin", "password")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .body(body)
                .contentType(ContentType.JSON)
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void addNewItemByAdmin_noAmountDefaultToZero() {
        String body = "{\"name\":\"flour\",\"description\":\"crushedgrain\",\"price\":\"15.00\"}";

        AddItemDto returnedItem = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin", "password")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .body(body)
                .contentType(ContentType.JSON)
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(AddItemDto.class);

        Assertions.assertEquals(0, returnedItem.getAmount());
    }

    @Test
    void addNewItemByMember_NoAuthorizationException() {
        String body = "{\"name\":\"flour\",\"description\":\"crushedgrain\",\"price\":\"15.00\",\"amount\":1}";

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
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

}
