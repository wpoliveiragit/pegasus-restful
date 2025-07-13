# GUIA DO PROJETO
Este guia tem como finalidade:
-[ ] Explicar a Arquitetura hexagonal (com modificações leves).
-[ ] Criar um projeto RESTful na Arquitetura Hexagonal e explicar sua funcionalidade.
-[ ] Explicar configurações mais convencionais no applycation.yml em APIs BackEnd.
-[ ] Explicar configurações no pom.xml.

## Índice
- [Arquitetura Hexagonal](./guia-arquitetura-hexagonal)
- [Funcionalidade](./guia-de-propriedades)
   - [User (CRUD)](#crud--user)
   - [Item (CRUD)](#crud--item)
- [Configurações](#configurações)
   - [pom.xml](#pomxml)
   - [application.yml](#applicationyml)

## Estrutura do projeto

```text
src
 ├── main
 │    ├── java
 │    │    ├── com
 │    │    │    ├── exemplo
 │    │    │    │    ├── controller
 │    │    │    │    │    ├── ItemController.java
 │    │    │    │    │    └── UserController.java
 │    │    │    │    ├── model
 │    │    │    │    │    ├── Item.java
 │    │    │    │    │    └── User.java
 │    │    │    │    ├── repository
 │    │    │    │    │    ├── ItemRepository.java
 │    │    │    │    │    └── UserRepository.java
 │    │    │    │    ├── security
 │    │    │    │    │    └── WebSecurityConfig.java
 │    │    │    │    └── service
 │    │    │    │         ├── ItemService.java
 │    │    │    │         └── UserService.java
 │    └── resources
 │         ├── application.yml
 │         └── data
 │              └── schema.sql
 └── pom.xml
```

## Funcionalidade

descrição

### CRUD :: User

### CRUD :: Item

## Configurações

### pom.xml

### application.yml

## Banco de dados no projeto

```yml
# Exemplo prático de um `datasource` configurado de DB dinamico (sem persistência)
spring.datasource.url: 'jdbc:h2:mem:testdb'
spring.datasource.driver-class-name: 'org.h2.Driver'
spring.datasource.username: 'sa'
spring.datasource.password: ''
```

###  

* `spring.datasource.url:`
    * `Descrição:` Define a URL de conexão com o DB.
    * `jdbc:h2`: Protocolo de conexão com DB h2 (H2, que é um banco em memória).
    * Exemplo 1: `'jdbc:h2:mem:testdb'`
        * `:mem`: Indica que o DB será em memória (não persistente).
        * `:testdb`: Nome do DB.
    * Exemplo 2: `'jdbc:h2:./src/main/resources/testdb'`
        * `:./src/main/resources/testdb`: Localização do DB em disco.

spring.datasource.url: 'jdbc:h2:./src/main/resources/testdb'

#### spring.datasource.url:

* spring.datasource.driver-class-name:
    * Nome da classe do driver JDBC.
* spring.datasource.username:
    * Nome de usuário para acessar o DB.
* spring.datasource.password:
    * Senha para acessar o DB.

spring.datasource.url: 'jdbc:h2:mem:testdb'
#spring.datasource.url: 'jdbc:h2:./src/main/resources/testdb;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE'

spring.datasource.driver-class-name: 'org.h2.Driver'
spring.datasource.username: 'sa'
spring.datasource.password: ''

# DATABASE - console do H2

spring.h2.console.enabled: true # Ativa o console no via navegador
spring.h2.console.path: /h2-console # Definição do path (ex.: http://localhost:8080/h2-console)

# JPA - configurações

spring.jpa.show-sql: true # Mostra o sql usado pelo JPA
spring.jpa.hibernate.ddl-auto: create

# none:_________ Não realiza nenhuma ação no DB.

# validate:_____ Verifica se o esquema do banco está consistente com as entidades, sem alterá-lo.

# update:_______ Sincroniza o esquema com as entidades. Mantem dados e tabelas existentes.

# create:_______ Gera o esquema do banco com base nas entidades. Apaga os dados anteriores.

# create-drop:__ Idem ao 'create', mas remove o esquema ao encerrar o aplicativo.

# LOG - CONFIGURAÇÃO

#logging.pattern.console: '[%d{yyyy-MM-dd HH:mm:ss}] [%-5level] [%logger{0}] :: %highlight(%msg%n)'
#logging.pattern.console: '%yellow([%d{yyyy-MM-dd HH:mm:ss}] [%-5level] [%logger{0}] :: %msg%n)'
#logging.pattern.console: '[%yellow(%d{yyyy-MM-dd HH:mm:ss})] [%highlight(%-5level)] [%cyan(%thread)] [%blue(%logger{36})] ::
%msg%n'

# LOG - STATUS

logging.level.org.springframework: INFO
logging.level.org.springframework.security: DEBUG
logging.level.com.exemplo.hexagonal: DEBUG

# LOG - DESATIVA SECURITY

logging.level.org.springframework.security.web.DefaultSecurityFilterChain: OFF
logging.level.org.springframework.security.web.authentication.AnonymousAuthenticationFilter: OFF
logging.level.org.springframework.security.web.FilterChainProxy: OFF
logging.level.org.springframework.security.web.savedrequest.HttpSessionRequestCache: OFF
logging.level.org.springframework.security.config.annotation.authentication.configuration.InitializeUserDetailsBeanManagerConfigurer:
OFF

# BANNER

app.property.java.version: '@java.version@'
app.property.compilation.build: '@compilation.build@'
app.groupId: '@project.groupId@'
app.artifactId: '@project.artifactId@'
app.version: '@project.version@'
app.name: '@project.name@'
app.description: '@project.description@'

