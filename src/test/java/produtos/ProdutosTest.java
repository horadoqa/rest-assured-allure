import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

public class ProdutosTest {

    @Test
    void deveListarProdutos() {

        RestAssured.baseURI = "https://serverest.dev";

        given()

        .when()
            .get("/produtos")

        .then()
            .statusCode(200)
            .body("quantidade", greaterThan(0))
            .body("produtos", not(empty()));
    }
}