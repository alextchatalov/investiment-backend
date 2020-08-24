package com.monitor.api.investiment;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InvestimentRestTest {

    @LocalServerPort
    private int port;

    @Test
    public void insertNewInvestiment() {
        String jsonInsert = "{\"investimentCode\":\"Teste\",\"type\":\"Ação\",\"amount\":\"12\",\"broker\":\"CLEAR\"}";

        given()
            .port(port)
            .contentType("application/json")
            .body(jsonInsert)
            .when()
            .post("/api/v1/investiment/newInvestiment")
            .then()
            .statusCode(201);
    }

}
