# API Automation Tests - Rest Assured

Projeto de automação de testes de API REST utilizando **Java + JUnit 5 + Rest Assured**, tendo como base a API de estudos **ServeRest**, que simula um ambiente de e-commerce.

O objetivo deste projeto é praticar automação de testes de serviços REST, validando cenários de negócio como:

* Cadastro de usuários;
* Login e autenticação;
* Cadastro e consulta de produtos;
* Atualização e exclusão de registros;
* Validações de regras de negócio;
* Execução automatizada via CI/CD.

---

# Tecnologias utilizadas

| Tecnologia     | Objetivo                                 |
| -------------- | ---------------------------------------- |
| Java           | Linguagem de programação                 |
| JUnit 5        | Framework de testes                      |
| Rest Assured   | Automação de testes API REST             |
| Maven          | Gerenciamento de dependências e execução |
| Hamcrest       | Validações de respostas                  |
| GitHub Actions | Pipeline CI/CD                           |

---

# O que é Rest Assured?

O **Rest Assured** é uma biblioteca Java utilizada para automatizar testes de APIs REST.

Ele permite criar requisições HTTP e validar respostas utilizando uma sintaxe simples e legível.

Documentação oficial:

https://rest-assured.io/

Primeiros passos:

https://github.com/rest-assured/rest-assured/wiki/GettingStarted

---

# O que pode ser validado com Rest Assured?

* Endpoints REST:

```
GET
POST
PUT
PATCH
DELETE
```

* Status HTTP:

```
200 OK
201 Created
400 Bad Request
401 Unauthorized
404 Not Found
```

* Headers;
* Corpo da resposta JSON/XML;
* Tempo de resposta;
* Regras de negócio;
* Autenticação;
* Tokens JWT.

---

# Estrutura do projeto

```text
src
└── test
    ├── java
    │
    │── model
    │   ├── Usuario.java
    │   └── Login.java
    │
    │── usuarios
    │   ├── CriarUsuarioTest.java
    │   ├── AtualizarUsuarioTest.java
    │   └── ExcluirUsuarioTest.java
    │
    │── login
    │   └── LoginTest.java
    │
    │── produtos
    │   └── ProdutosTest.java
    │
    └── resources
```

---

# Exemplo de teste

## Consultar produtos

Exemplo utilizando o método HTTP GET:

```java
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

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
```

---

# ServeRest

O ServeRest é uma API REST criada para estudos e testes de automação.

Ela simula funcionalidades de um e-commerce.

Documentação:

https://serverest.dev/

---

# Endpoints utilizados

| Método | Endpoint         | Objetivo          |
| ------ | ---------------- | ----------------- |
| GET    | `/usuarios`      | Listar usuários   |
| POST   | `/usuarios`      | Criar usuário     |
| PUT    | `/usuarios/{id}` | Atualizar usuário |
| DELETE | `/usuarios/{id}` | Excluir usuário   |
| POST   | `/login`         | Autenticação      |
| GET    | `/produtos`      | Listar produtos   |

---

# Cenários automatizados

## Usuários

* Criar usuário com sucesso;
* Consultar usuário;
* Atualizar usuário;
* Excluir usuário;
* Validar e-mail duplicado.

---

## Login

* Login com usuário válido;
* Login com senha inválida;
* Validação do token JWT;
* Usuário inexistente.

---

## Produtos

* Listar produtos;
* Buscar produto por ID;
* Criar produto;
* Atualizar produto;
* Excluir produto;
* Validar campos obrigatórios.

---

## Carrinho

* Adicionar produto;
* Remover produto;
* Finalizar compra;
* Validar estoque.

---

# Regras de negócio validadas

Exemplos:

* Não permitir e-mail duplicado;
* Não permitir usuário sem senha;
* Não autenticar usuário inválido;
* Validar existência do produto;
* Validar permissões;
* Validar token de acesso.

---

# Executando os testes

## Executar todos os testes

```bash
mvn clean test
```

O Maven irá:

1. Compilar o projeto;
2. Baixar dependências;
3. Localizar classes com `@Test`;
4. Executar os testes;
5. Gerar os relatórios.

---

# Executar um teste específico

Criar usuário:

```bash
mvn test -Dtest=CriarUsuarioTest
```

Login:

```bash
mvn test -Dtest=LoginTest
```

---

# Relatório de testes

Após a execução:

```bash
mvn test
```

Os relatórios ficam disponíveis em:

```text
target/
└── surefire-reports/

    ├── TEST-CriarUsuarioTest.xml
    └── TEST-LoginTest.xml
```

---

# Executando pelo VS Code

Extensões necessárias:

* Extension Pack for Java
* Test Runner for Java

Executar:

```
Clique no método @Test

▶ Run Test
```

---

# Pipeline CI/CD

Execução automatizada utilizando GitHub Actions:

```mermaid
flowchart LR

    CRON["⏰ Execução agendada"]

    GH["GitHub Actions"]

    JAVA["Java + Maven"]

    RA["Rest Assured"]

    API["ServeRest API"]

    REPORT["Relatório"]

    CRON --> GH
    GH --> JAVA
    JAVA --> RA
    RA --> API
    API --> REPORT
```

---

# Boas práticas aplicadas

* Separação de responsabilidades;
* Modelos utilizando POJO;
* Dados dinâmicos para testes;
* Validação de respostas;
* Organização por domínio;
* Testes independentes;
* Preparação de dados antes dos cenários;
* Integração com CI/CD.

---

# Objetivo do projeto

Este projeto representa uma base prática de **QA Automation**, simulando cenários encontrados em APIs reais de e-commerce.

Tecnologias:

```
Java
+
JUnit 5
+
Rest Assured
+
ServeRest
+
GitHub Actions
```

Essa stack permite evoluir desde testes básicos de API até uma arquitetura completa de automação utilizada em ambientes profissionais.
