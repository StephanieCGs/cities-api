# cities-api

 Checagem de países, estados e cidades e cálculo de distância entre cidades.



## Description

Desenvolver uma aplicação web em Node.js com banco de dados MySQL. A aplicação consistirá somente de uma página com dois painéis: no painel posicionado a esquerda, o usuário poderá cadastrar novos comentários. No painel da direita todos os comentários cadastrados devem ser listados, com um botão ao lado de cada um que ao ser clicado executará um áudio de leitura do comentário.



## Requirements

* Java 8
* Docker
* IntelliJ Community
* Heroku CLI



## Getting Started/Project Setup

### DataBase

#### Postgres

* [Postgres Docker Hub](https://hub.docker.com/_/postgres)

```shell script
docker run --name cities-db -d -p 5432:5432 -e POSTGRES_USER=postgres_user_city -e POSTGRES_PASSWORD=super_password -e POSTGRES_DB=cities postgres
```

### Populate

* Dados usados do seguinte repositório (clonar repositório na workspace): [data](https://github.com/chinnonsantos/sql-paises-estados-cidades)
* Após clonar o repositório acima na sua workspace, acessar pelo terminal e executar os seguintes códigos:

```shell script
cd ~/workspace/sql-paises-estados-cidades/PostgreSQL

docker run -it --rm --net=host -v ${PWD}:/tmp postgres /bin/bash

psql -h localhost -U postgres_user_city cities -f /tmp/pais.sql
psql -h localhost -U postgres_user_city cities -f /tmp/estado.sql
psql -h localhost -U postgres_user_city cities -f /tmp/cidade.sql

psql -h localhost -U postgres_user_city cities

CREATE EXTENSION cube; 
CREATE EXTENSION earthdistance;
```

* Obs.: cube e earthdistance são extensões que permitirão com que o Banco de Dados calcule a distância entre duas cidades informadas.

 

## Tecnologies

- [Java](https://www.java.com/pt-BR/) 

- [Gradle](https://gradle.org/)
- [Docker](https://www.docker.com/)
- [Postgresql](https://www.postgresql.org/)



### Executing program

* Abrir no IntelliJ Community o projeto e executar. 

* No navegador acesse http://localhost:3000/index (ou a porta de preferência) para ver o funcionamento.

  * Retorna países: http://localhost:8080/countries
* Retorna estados do Brasil: http://localhost:8080/states
  * Retorna cidades do Brasil: http://localhost:8080/cities
* Retorna a distância **em milhas** entre duas cidades (é feito pela extensão earthdistance do postgresql): http://localhost:8080/distances/by-points?from=IdCidadeUm&to=IdCidadeDois
  
  * Exemplo - Distância entre Ibaté/SP e São Carlos/SP em milhas:  http://localhost:8080/distances/by-points?from=4929&to=5254
  * Retorna a distância **em metros** entre duas cidades (é feito pela extensão cube do postgresql): http://localhost:8080/distances/by-cube?from=IdCidadeUm&to=IdCidadeDois

    * Exemplo - Distância entre Ibaté/SP e São Carlos/SP em metros:  http://localhost:8080/distances/by-cube?from=4929&to=5254
* Retorna a distância entre duas cidades **na unidade desejada (METERS, KILOMETERS, MILES)** / (Cálculo feito no programa): http://localhost:8080/distances/by-math?from=IdCidadeUm&to=IdCidadeUm&unit=UnidadeDeMedida
    * Exemplo - Distância entre Ibaté/SP e São Carlos/SP em metros:  http://localhost:8080/distances/by-math?from=4929&to=5254&unit=METERS



http://localhost:8080/distances/by-points?from=**IdCidadeUm**&to=**IdCidadeDois**

<span>https://example.com</span>

