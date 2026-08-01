# Fluxo LOGIN

O fluxo de **Login** consiste em autenticar um usuário já cadastrado e obter um token de autorização que poderá ser utilizado para acessar endpoints protegidos da API.

## Fluxo do Login

```mermaid
sequenceDiagram
    participant Cliente
    participant API
    participant Banco

    Cliente->>API: POST /login (email + senha)
    API->>Banco: Buscar usuário pelo e-mail
    Banco-->>API: Dados do usuário
    API->>API: Validar senha
    API-->>Cliente: 200 + Token de autorização
```

---

## Passo a passo

### 1. Criar um usuário

Antes de realizar o login, é necessário que exista um usuário cadastrado.

```text
POST /usuarios
```

Resposta esperada:

```json
{
  "message": "Cadastro realizado com sucesso",
  "_id": "123456789"
}
```

---

### 2. Enviar as credenciais

O cliente envia uma requisição para o endpoint de login.

```text
POST /login
```

Body:

```json
{
  "email": "usuario@email.com",
  "password": "123456"
}
```

---

### 3. Validação da API

A API executa as seguintes verificações:

* O e-mail existe?
* A senha está correta?
* O usuário pode autenticar?

Se todas as validações forem aprovadas, a autenticação é realizada.

---

### 4. Retorno da API

Resposta de sucesso:

```json
{
  "message": "Login realizado com sucesso",
  "authorization": "Bearer eyJhbGciOiJIUzI1NiIs..."
}
```

O campo `authorization` contém o token que será utilizado nas próximas requisições autenticadas.

---

## Fluxo completo

```mermaid
flowchart TD
    A["Criar Usuário"] --> B["POST /login"]
    B --> C["Validar e-mail"]
    C --> D["Validar senha"]

    D -->|Válida| E["Gerar Token"]
    E --> F["Retornar HTTP 200"]

    D -->|Inválida| G["Retornar HTTP 401"]
```

---

## Cenário de sucesso (Gherkin)

```gherkin
Feature: Login

  Scenario: Realizar login com sucesso
    Given que existe um usuário cadastrado
    When uma requisição POST é enviada para "/login" com email e senha válidos
    Then a API deve retornar o status 200
    And deve retornar a mensagem "Login realizado com sucesso"
    And deve retornar um token de autorização
```

---

## Cenário de falha

```gherkin
Feature: Login

  Scenario: Login com senha inválida
    Given que existe um usuário cadastrado
    When uma requisição POST é enviada para "/login" com senha incorreta
    Then a API deve retornar o status 401
    And deve retornar a mensagem "Email e/ou senha inválidos"
```

---

## Validações recomendadas no teste automatizado

Além de verificar o código de status, é recomendável validar:

* **HTTP Status:** `200`
* **Mensagem:** `"Login realizado com sucesso"`
* **Token de autorização:** não nulo (`authorization`)
* **Formato do token:** começa com `"Bearer "` (se a API utilizar esse prefixo)
* **Tempo de resposta:** dentro do limite esperado (por exemplo, menos de 1 segundo)

Esse fluxo representa um cenário completo de autenticação e serve como base para outros testes que dependem de um usuário autenticado, como cadastro de produtos, gerenciamento de carrinho ou consultas protegidas.
