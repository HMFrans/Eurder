package com.switchfully.eurder.itemTests;


import com.switchfully.eurder.service.items.dto.AddItemDto;
import com.switchfully.eurder.service.orders.dto.ItemGroupReturnDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class ItemIntegrationTest {
    @LocalServerPort
    private int port;

    @Test
    void addItemAsAdmin_happyPath() {
        AddItemDto body = new AddItemDto("bees",
        "stingy animals",
                BigDecimal.valueOf(15.00),
                1);

        AddItemDto result = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin@test.com", "password")
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

        Assertions.assertThat(result.getName()).isEqualTo("bees");
    }

    @Test
    void addItemAsAdmin_itemAlreadyExists() {
        AddItemDto body = new AddItemDto("flour",
                "stingy animals",
                BigDecimal.valueOf(15.00),
                1);

        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin@test.com", "password")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .body(body)
                .contentType(ContentType.JSON)
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
