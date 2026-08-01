package login;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.Login;
import model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

public class LoginTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://serverest.dev";
    }

    @Feature("Usuários")
    @Story("Login de usuário")
    @Description("Validar login de usuário com sucesso")
    @Test
    void deveRealizarLoginComSucesso() {

        // Arrange
        String email = "usuario" + System.currentTimeMillis() + "@email.com";
        String senha = "1q2w3e4r";

        Usuario usuario = new Usuario();
        usuario.setNome("Hora do QA");
        usuario.setEmail(email);
        usuario.setPassword(senha);
        usuario.setAdministrador("true");

        // Cria o usuário
        given()
            .contentType(ContentType.JSON)
            .body(usuario)
        .when()
            .post("/usuarios")
        .then()
            .statusCode(201);

        // Monta o login
        Login login = new Login();
        login.setEmail(email);
        login.setPassword(senha);

        // Act + Assert
        given()
            .contentType(ContentType.JSON)
            .body(login)
        .when()
            .post("/login")
        .then()
            .statusCode(200)
            .body("message", equalTo("Login realizado com sucesso"))
            .body("authorization", notNullValue());
    }
}