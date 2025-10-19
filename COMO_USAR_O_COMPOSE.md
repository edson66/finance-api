# Guia Rápido: Usando o Docker Compose para seu Ambiente Local

Este arquivo `docker-compose.yml` vai criar e conectar todo o ambiente de desenvolvimento (API + Banco de Dados) com um único comando.

## O Que Este Arquivo Faz?

Ele define dois serviços: `db` e `api`.

### Serviço `db` (Banco de Dados)

- **O Quê:** Um contêiner rodando **MySQL 8.0**.
- **Configuração:**
    - Cria um banco de dados chamado `finance-api-db`.
    - Configura o usuário `root` com a senha `root`.
    - Expõe a porta `3306` para a sua máquina. **Você pode se conectar com o DBeaver em `localhost:3306` usando o usuário `root` e a senha `root`**.
    - Salva todos os dados para que você não perca nada.

### Serviço `api` (A Aplicação)

- **O Quê:** Um contêiner rodando a aplicação Spring Boot, construído a partir do `Dockerfile`.
- **Configuração:**
    - Garante que a API só suba **depois** que o banco de dados estiver pronto.
    - Injeta as variáveis de ambiente `DATASOURCE_URL`, `DATASOURCE_USERNAME`, e `DATASOURCE_PASSWORD` com os valores `jdbc:mysql://db:3306/finance-api-db`, `root` e `root`.
    - Injeta a variável `TOKEN_SENHA` com o valor `222222`.

---

## Comandos Essenciais

Abra seu terminal na pasta raiz do projeto.

### Para Ligar o Ambiente

`docker-compose up --build`

### Para Desligar o Ambiente

1.  Pressione `Ctrl + C` no terminal onde os logs estão aparecendo.
2.  Depois, para remover os contêineres, rode:
    `docker-compose down`