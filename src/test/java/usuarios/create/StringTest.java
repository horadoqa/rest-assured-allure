import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

public class StringTest {

    @Feature("Usuários")
    @Story("Cadastro de usuário")
    @Description("Validar criação de usuário com sucesso")
    @Test
    void deveCriarUsuarioComSucesso() {

        RestAssured.baseURI = "https://serverest.dev";

        String body = """
        {
            "nome": "Hora do QA",
            "email": "horadoqa@email.com",
            "password": "1q2w3e4r",
            "administrador": "true"
        }
        """;

        given()
            .contentType("application/json")
            .body(body)

        .when()
            .post("/usuarios")

        .then()
            .statusCode(201)
            .body("message", equalTo("Cadastro realizado com sucesso"))
            .body("_id", notNullValue());
    }
}