package com.switchfully.eurder.orderTests;

import com.switchfully.eurder.domain.items.ItemRepository;
import com.switchfully.eurder.service.orders.dto.ReturnOrderDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class OrderIntegrationTest {
    @LocalServerPort
    private int port;
    @Autowired
    ItemRepository itemRepository;

    @Test
    void createOrderByCustomer_HappyPath() {
        String body = "[{\"name\":\"flour\",\"amount\":2},{\"name\":\"egg\",\"amount\":1}]";

        ReturnOrderDto result = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("customer@test.com", "password")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .body(body)
                .contentType(ContentType.JSON)
                .post("/order")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(ReturnOrderDto.class);

        Assertions.assertThat(result.getOrderId()).isNotNull();
        Assertions.assertThat(result.getTotalCost()).isEqualTo("11.0");
        Assertions.assertThat(itemRepository.findByName("egg").getAmountInStock()).isEqualTo(4);

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
                .basic("customer@test.com", "password")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .body(body)
                .contentType(ContentType.JSON)
                .post("/order")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void getAllOrdersForSpecificMember_happyPath() {
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
                .get("/order")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }


}
