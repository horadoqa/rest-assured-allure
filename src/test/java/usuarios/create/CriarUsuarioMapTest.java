//  Utilizando um Map (mais recomendado)
// Ao invés de escrever o JSON como texto, você pode construir o corpo da requisição com um Map, o que facilita a manutenção

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

public class CriarUsuarioMapTest {

    @Feature("Usuários")
    @Story("Cadastro de usuário")
    @Description("Validar criação de usuário com sucesso")
    @Test
    void deveCriarUsuarioComSucesso() {

        RestAssured.baseURI = "https://serverest.dev";

        String email = "usuario" + System.currentTimeMillis() + "@email.com";

        Map<String, Object> usuario = new HashMap<>();
        usuario.put("nome", "Hora do QA");
        // usuario.put("email", "joao.silva.teste123@email.com");
        usuario.put("email", email);
        usuario.put("password", "1q2w3e4r");
        usuario.put("administrador", "true");

        given()
            .contentType("application/json")
            .body(usuario)

        .when()
            .post("/usuarios")

        .then()
            .statusCode(201)
            .body("message", equalTo("Cadastro realizado com sucesso"))
            .body("_id", notNullValue());
    }
}