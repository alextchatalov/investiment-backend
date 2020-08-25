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

    @Test
    public void deleteInvestiment() {
        given()
                .port(port)
                .contentType("application/json")
                .when()
                .delete("/api/v1/investiment/delete/Teste")
                .then()
                .statusCode(200);
    }

    @Test
    public void updateInvestiment() {
        String json = "{\"investimentCode\":\"MDIA3 - M.DIASBRANCO\",\"type\":\"Ação\",\"broker\":\"CLEAR CORRETORA\",\"firstDateApplication\":\"2020-01-16\",\"appliedAmount\":1097,\"balance\":1148,\"rentail\":3,\"portfolioShare\":1,\"amount\":\"2\"}";
        given()
                .port(port)
                .contentType("application/json")
                .body(json)
                .when()
                .patch("/api/v1/investiment/updateInvestimet/MDIA3%20-%20M.DIASBRANCO")
                .then()
                .statusCode(200);
    }

}
