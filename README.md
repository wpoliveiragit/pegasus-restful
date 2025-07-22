# README
O que este projeto apresenta?

# Arquitetura em Camadas
# Banco de dados em H2
- Configuração do banco no application.yml
# Openapi Contrato Login CRUD
- Configuração do Pom.xml
- Criação de estrutura em resources
- Criação do contrato REST CRUD
- Criação de um contrato para centralização de textos (não constantes) do projeto
- Criação de um contrato para criar modelos de entrada do contrato principal para as regras de negócio
- Banco H2

→

## Adicionar
- Documentação de configuração do banco de dados
- Documentação do openapi


# HELP
**LEMBRETES**
* Testar paginação com size = 0 (zero)
* Criar um check list para cada endpoint


#LOG
info → requisições externas, início e/ou fim de processo.
debug → valores internos, chamadas de banco.
error → exceções capturadas.

## PAGINACAO 


## FIND ALL (PAGE)
* 200: OK
  - Retorno esperado
* 400: BAD REQUEST 
  - pagina com indice negativa
  - 'Invalid pagination parameters: page: x
* 500: INTERNAL SERVER ERRO
    - Algum tipo de erro inexperado

# FIND BY ID
* 200: OK
* 404: NOT FOUND
* 500: INTERNAL SERVER ERRO

# CREATE
* 201: CREATED
* 400: BAD REQUEST {nome ou Raça ou Classe são obrigatórios}
* 500: INTERNAL SERVER ERRO

# UPDATE
* 200: OK
* 404: Not Found
* 400: Bad Request
* 500: INTERNAL SERVER ERRO

# DELETE
* 204: No Content
* 404: Not Found
* 500: INTERNAL SERVER ERRO



