# Projeto Finance (API Back-end)

![Java](https://img.shields.io/badge/Java-21-blue?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-blue?logo=docker&logoColor=white)
![AWS](https://img.shields.io/badge/AWS%20ECS-orange?logo=amazon-aws&logoColor=white)

API RESTful completa desenvolvida em **Java com Spring Boot** para a aplicação [Projeto Finance](https://github.com/edson66/projeto-finance-front). Esta API é o cérebro por trás do aplicativo, responsável por toda a lógica de negócio, gerenciamento de dados e segurança.

Esta API gerencia:
* Autenticação e autorização de usuários via **Tokens JWT**.
* **CRUD completo** de transações financeiras (receitas e despesas).
* Geração de **sumários financeiros** (saldo total e por período).
* Validação de dados e regras de negócio.

##  Funcionalidades Principais

* **Segurança:** Autenticação stateless com Spring Security e JSON Web Tokens (JWT).
* **Endpoints Protegidos:** Rotas que exigem autenticação para acesso.
* **Validação:** Validações de dados de entrada (ex: regras de senha, dados de transação) usando o Spring Validation.
* **ORM:** Uso do Spring Data JPA para persistência de dados.
* **Documentação:** API 100% documentada com **Swagger (OpenAPI)**.
* **Sumarização:** Endpoints dedicados para calcular saldos, receitas e despesas totais ou por período.
* **Testes:** Cobertura de testes unitários e de integração para garantir a lógica da API.
* **Containerização:** Aplicação totalmente containerizada com **Docker** para deploy na nuvem.
* **CORS:** Configuração de CORS para permitir acesso de múltiplos front-ends (ex: `localhost` e o deploy da Vercel).

##  Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3**
* **Spring Security** (Autenticação e Autorização JWT)
* **Spring Data JPA** (Persistência de dados)
* **Maven** (Gerenciador de dependências)
* **Lombok** (Redução de boilerplate)
* **Swagger / Springdoc-openapi** (Documentação da API)
* **JUnit 5** (Testes)
* **Docker** (Containerização)
* **AWS EC2** (Hospedagem e Deploy)
* **Mysql** (Banco de Dados)

##  Documentação da API (Swagger)

Toda a API está documentada e pode ser testada interativamente através do Swagger UI.

Acesse: ➡️ **[api.finance-app-edson.uk/swagger-ui.html](https://api.finance-app-edson.uk/swagger-ui.html)**

Ou com a aplicação rodando localmente, acesse:
➡️ **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

##  Como Executar Localmente

**Pré-requisitos:**
* JDK 21
* Maven 3.9+
* Um banco de dados MySQL rodando e acessível.

**Passos:**

1.  Clone o repositório:
    ```bash
    git clone [https://github.com/edson66/projeto-finance.git](https://github.com/edson66/projeto-finance.git)
    cd projeto-finance
    ```

2.  Configure as variáveis de ambiente. No seu `src/main/resources/application.properties` (ou `application.yml`), configure as variáveis essenciais:
    ```properties
    # Configuração do Banco de Dados
    spring.datasource.url=jdbc:postgresql://localhost:5432/finance_db
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha

    # Chave secreta para assinatura dos tokens JWT
    api.security.token.secret=sua_chave_secreta_aqui
    ```

3.  Instale as dependências e rode a aplicação:
    ```bash
    mvn spring-boot:run
    ```

A API estará disponível em `http://localhost:8080`.

## 🐳 Como Executar com Docker (Docker Compose)

A forma mais simples de executar a aplicação (junto com o banco de dados e outros serviços) é utilizando o Docker Compose.

Este projeto inclui um arquivo `docker-compose.yml` pré-configurado.

Para instruções detalhadas sobre como configurar as variáveis de ambiente necessárias e inicializar os containers, por favor, consulte o arquivo:

➡️ **`COMO_USAR_O_COMPOSE`**

...que está na raiz deste repositório. Ele contém o passo a passo exato para subir todo o ambiente de forma rápida e correta.

De forma geral, após seguir as instruções do arquivo, o comando para iniciar a aplicação será:

```bash
docker-compose up --build







