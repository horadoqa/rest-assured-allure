package usuarios;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

public class AtualizarUsuarioTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://serverest.dev";
    }

    @Feature("Usuários")
    @Story("Update de dados do usuário")
    @Description("Validar atualização de dados do usuário com sucesso")
    @Test
    void deveAtualizarUsuarioComSucesso() {

        // Arrange
        String email = "usuario" + System.currentTimeMillis() + "@email.com";

        Usuario usuario = new Usuario();
        usuario.setNome("João Silva");
        usuario.setEmail(email);
        usuario.setPassword("123456");
        usuario.setAdministrador("true");

        // Cria o usuário
        String idUsuario =
            given()
                .contentType(ContentType.JSON)
                .body(usuario)
            .when()
                .post("/usuarios")
            .then()
                .statusCode(201)
                .extract()
                .path("_id");

        // Atualiza os dados
        usuario.setNome("João Silva Atualizado");
        usuario.setPassword("654321");

        // Act + Assert
        given()
            .contentType(ContentType.JSON)
            .body(usuario)
        .when()
            .put("/usuarios/" + idUsuario)
        .then()
            .statusCode(200)
            .body("message", equalTo("Registro alterado com sucesso"));

        // Validação adicional
        given()
        .when()
            .get("/usuarios/" + idUsuario)
        .then()
            .statusCode(200)
            .body("nome", equalTo("João Silva Atualizado"))
            .body("email", equalTo(email))
            .body("administrador", equalTo("true"));
    }
}