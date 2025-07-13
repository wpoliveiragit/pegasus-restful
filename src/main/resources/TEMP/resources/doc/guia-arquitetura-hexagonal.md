# Guia Arquitetura Hexagonal

A Arquitetura Hexagonal, também conhecida como Arquitetura de Portos e Adaptadores, é um estilo de design de software
que tem como objetivo isolar o núcleo da aplicação (lógica de negócios) das dependências externas, como bancos de dados,
APIs e interfaces de usuário.

Ela utiliza portos (interfaces) para interligar as portas de entrada da aplicação (como controladores REST, listeners e
schedulers) aos adaptadores que acessam recursos externos (como bancos de dados e mensageria). Essa separação torna o
código mais robusto, testável, organizado e elegante.

## Estrutura principal da arquitetura

A aplicação é organizada em três pacotes principais:

- app: Contém a comunicação com o mundo exterior (portas de entrada).
- domain: Contém a lógica de negócios, sendo o núcleo da aplicação (core).
- infra: Contém os recursos externos utilizados pela aplicação (adaptadores).

## Pacote app

Este pacote representa as portas de entrada do projeto, responsáveis pela comunicação com o mundo externo, como
controladores REST (API HTTP), schedulers e sistemas de mensageria (ex: Kafka, RabbitMQ). As classes deste pacote podem
receber anotações como @Component.

**Subpacotes:**

- resources: Porta de entrada para APIs HTTP (controladores REST).
- consumer: Porta de entrada para listeners de mensageria, como Kafka ou filas MQ.
- scheduler: Porta de entrada para tarefas agendadas.
- advice: Controle centralizado de exceções da aplicação.
- entrypoint: Representa a entrada de comunicação de uma biblioteca externa. Os nomes dos subpacotes podem variar de
  acordo com o serviço oferecido.
- dto: Classes utilizadas para representar os parâmetros de entrada e saída das portas de entrada.

## Pacote domain

Este pacote representa o núcleo da aplicação, contendo toda a lógica de negócios.

**Subpacotes:**

- adapter: Interfaces que definem a conexão entre o core e os adaptadores externos implementados no pacote infra.
    - São implementadas em infra. Injetadas no core via construtor.
- catalog: Enums utilizados no domínio.
- core: Onde reside a lógica de negócios.
    - Implementa as interfaces port.
    - Recebe os adapter por injeção de dependência.
    - Só pode utilizar recursos nativos do Java.
    - Só pode lançar exceções do tipo nativo ou definidas no próprio domínio.
- exceptioncore: Exceções definidas pelo domínio, lançadas exclusivamente pelo core. Podem ser interceptadas pelo pacote
  advice.
- model: Representa os tipos de dados utilizados como entrada e saída nos adapter e port.
    - A entrada de uma port deve ser um model, convertido por um mapper a partir de um DTO.
    - A saída de uma port deve retornar um model, convertido para DTO por um mapper.
    - Os parâmetros de entrada e saída dos adapter também devem ser model, convertidos via mapper.
- port: Interfaces que conectam o core às portas de entrada (como APIs, schedulers ou consumers).
    - São implementadas no core.
    - Injetadas nas portas de entrada via construtor.

## Pacote infra

Este pacote representa os recursos externos utilizados pela aplicação. Contém implementações concretas das interfaces
adapter e configurações auxiliares.

**Subpacotes:**

- config: Contém a criação de todos os beans da aplicação.
    - adapter: Implementações das interfaces adapter utilizadas no domínio.
    - mapper: Classes anotadas com @Component, @Mapper, etc.
    - port: Classes de configuração (@Configuration) que criam beans de port com @Bean.
    - security: Define regras de acesso e autenticação. Embora lide com controle de acesso, faz parte da infraestrutura
      pois
      depende de bibliotecas externas.
    - swagger: Configurações do Swagger para documentação da API. É um recurso auxiliar da infraestrutura, não uma porta
      de
      entrada.
- provider: Pacote dos recursos externos, como Kafka, bancos de dados, filas MQ, etc.
    - kafka: Configurações e uso do Kafka.
        - event: Modelos de dados usados com Kafka.
    - repository: Configurações e uso de repositórios (ex: JPA).
        - entity: Entidades persistidas nos repositórios.
- type: Enums utilizados nos pacotes app e infra.
- method: Classes utilitárias com métodos estáticos usados nos pacotes app e infra.
- value: Guarda classes de constantes de variáveis primitivas (boolean, char, byte, short, int, long, float, double,
  String)