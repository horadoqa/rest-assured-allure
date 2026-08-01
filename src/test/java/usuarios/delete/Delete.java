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

public class ExcluirUsuarioTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://serverest.dev";
    }

    @Feature("Usuários")
    @Story("Deleção de usuário")
    @Description("Validar deleção de usuário com sucesso")
    @Test
    void deveExcluirUsuarioComSucesso() {

        // Arrange
        String email = "usuario" + System.currentTimeMillis() + "@email.com";

        Usuario usuario = new Usuario();
        usuario.setNome("João Silva");
        usuario.setEmail(email);
        usuario.setPassword("123456");
        usuario.setAdministrador("true");

        // Cria o usuário e captura o ID
        String idUsuario =
            given()
                .contentType(ContentType.JSON)
                .body(usuario)
            .when()
                .post("/usuarios")
            .then()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"))
                .extract()
                .path("_id");

        // Act + Assert
        given()
        .when()
            .delete("/usuarios/" + idUsuario)
        .then()
            .statusCode(200)
            .body("message", equalTo("Registro excluído com sucesso"));
    }
}