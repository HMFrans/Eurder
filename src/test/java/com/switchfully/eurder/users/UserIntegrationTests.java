package com.switchfully.eurder.users;

import com.switchfully.eurder.users.domain.NewMemberDto;
import com.switchfully.eurder.users.domain.ReturnMemberDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTests {

    @LocalServerPort
    private int port;

    @Test
    void AddNewMember_HappyPath() {
        NewMemberDto newMemberDto = new NewMemberDto("Spongebob",
                "Squarepants",
                "spongebob@hotmail.com",
                "Bikini Bottom",
                "1000",
                "waterstreet",
                "1",
                "",
                "555-58");

        String body = "{\"firstName\":\"Spongebob\",\"lastName\":\"Squarepants\",\"emailAddress\":\"Squarepants@hotmail.com\",\"city\":\"BikiniBottom\",\"postalCode\":\"1000\",\"streetName\":\"waterstreet\",\"houseNumber\":\"1\",\"additionalInfo\":\"string\",\"phoneNumber\":\"string\"}";

        ReturnMemberDto returnMemberDto = RestAssured
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
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(ReturnMemberDto.class);

    }


}
