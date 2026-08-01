import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

public class UserTest {

    @Feature("Usuários")
    @Story("Lista de usuários")
    @Description("Validar lista de usuários com sucesso")
    @Test
    void deveListarUsuarios() {

        RestAssured.baseURI = "https://serverest.dev";

        given()

        .when()
            .get("/usuarios")

        .then()
            .statusCode(200)
            .body("quantidade", greaterThan(0))
            .body("usuarios", not(empty()));
    }
}