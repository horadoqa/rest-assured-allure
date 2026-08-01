package usuarios;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

public class CriarUsuarioTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://serverest.dev";
    }

    @Feature("Usuários")
    @Story("Cadastro de usuário")
    @Description("Validar criação de usuário com sucesso")
    @Test
    void deveCriarUsuarioComSucesso() {

        String email = "usuario" + System.currentTimeMillis() + "@email.com";

        Usuario usuario = new Usuario();
        usuario.setNome("João Silva");
        usuario.setEmail(email);
        usuario.setPassword("123456");
        usuario.setAdministrador("true");

        given()
            .contentType(ContentType.JSON)
            .body(usuario)
        .when()
            .post("/usuarios")
        .then()
            .statusCode(201)
            .body("message", equalTo("Cadastro realizado com sucesso"))
            .body("_id", notNullValue());
    }
}