<h1 align="center">
  <img alt="banner" title="banner" src="banner.png" />
</h1>

# :computer: livraria-api
API REST com Spring Boot para um sistema de gestão de uma livraria online.

Este projeto está sendo desenvolvido ao longo do Bootcamp Java Alura 2021.

### :bookmark_tabs: Tabela de conteúdos
* [Status do projeto](#status)
* [Tecnologias](#tecnologias)
* [Features](#features)
* [Pré-requisitos](#requisitos)
* [Executando a aplicação](#executando)
* [Testando a aplicação](#testando)
* [Licença](#licenca)

<a name="status"/></a>
### :hourglass: Status do projeto
Em desenvolvimento.

<a name="tecnologias"/></a>
### :hammer_and_wrench: Tecnologias

As seguintes ferramentas são utilizadas no desenvolvimento deste projeto:

- [Java](https://www.oracle.com/java/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Boot Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
- [Spring Boot Starter Validation](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation)
- [Spring Boot Starter Data JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
- [Spring Boot Starter Test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test)
- [Spring Boot DevTools](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools)
- [Flyway](https://flywaydb.org/)
- [Lombok](https://projectlombok.org/)
- [ModelMapper](http://modelmapper.org/)
- [Maven](https://maven.apache.org/)
- [MySQL](https://www.mysql.com/)
- [Git](https://git-scm.com/)
- [Docker](http://modelmapper.org/)

<a name="features"/></a>
### :page_with_curl: Features
- [x] Cadastro de autores
- [x] Cadastro de livros
- [x] Listagem de autores
- [x] Listagem de livros
- [x] Relatório de quantidade de livros por autor 

<a name="requisitos"/></a>
### :pencil: Pré-requisitos

Antes de começar, você precisa ter instalado em sua máquina as seguintes ferramentas:
- [Git](https://git-scm.com/)
- [Docker](https://www.docker.com/)

Além disso, você deve utilizar uma ferramenta para testar a API, como, por exemplo, o [Postman](https://www.postman.com/).

<a name="executando"/></a>
### :rocket: Executando a aplicação

```bash
# Clone este repositório
$ git clone https://github.com/antonioeloy/livraria-api.git

# Execute o container da aplicação
$ docker-compose up

# A aplicação iniciará na porta 8080
```

<a name="testando"/></a>
### :gear: Testando a aplicação

- <strong>GET localhost:8080/autores</strong> --> retorna a lista de autores (com paginação).
```json
{
    "content": [
        {
            "id": 1,
            "nome": "André da Silva",
            "email": "autor@email.com.br",
            "dataNascimento": "01/01/1989"
        },
        {
            "id": 2,
            "nome": "Fernanda Nogueira",
            "email": "autor@email.com.br",
            "dataNascimento": "01/01/1989"
        },
        {
            "id": 3,
            "nome": "Juliana Carvalho",
            "email": "autor@email.com.br",
            "dataNascimento": "01/01/1989"
        },
        {
            "id": 4,
            "nome": "Rita de Assis",
            "email": "autor@email.com.br",
            "dataNascimento": "01/01/1989"
        },
        {
            "id": 5,
            "nome": "Rodrigo de Souza",
            "email": "autor@email.com.br",
            "dataNascimento": "01/01/1989"
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 20,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 5,
    "last": true,
    "size": 20,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "numberOfElements": 5,
    "first": true,
    "empty": false
}
```

- <strong>POST localhost:8080/autores</strong> --> cadastra um novo autor.
```json
{
    "nome":"Autor 1",
    "email": "autor1@email.com.br",
    "dataNascimento": "13/06/1989",
    "minicurriculo": "Principais obras do autor 1"
}
```

- <strong>GET localhost:8080/livros</strong> --> retorna a lista de livros (com paginação).
```json
{
    "content": [
        {
            "id": 1,
            "titulo": "Aprenda Java em 21 dias",
            "dataLancamento": "12/03/2004",
            "numeroPaginas": 100
        },
        {
            "id": 2,
            "titulo": "Como ser mais produtivo",
            "dataLancamento": "21/04/2004",
            "numeroPaginas": 100
        },
        {
            "id": 3,
            "titulo": "Aprenda a falar em público",
            "dataLancamento": "01/07/2004",
            "numeroPaginas": 100
        },
        {
            "id": 4,
            "titulo": "Otimizando seu tempo",
            "dataLancamento": "10/12/2004",
            "numeroPaginas": 100
        },
        {
            "id": 5,
            "titulo": "Como fazer bolos incríveis",
            "dataLancamento": "12/09/2008",
            "numeroPaginas": 100
        },
        {
            "id": 6,
            "titulo": "Investindo em ações na bolsa de valores",
            "dataLancamento": "21/12/2010",
            "numeroPaginas": 100
        },
        {
            "id": 7,
            "titulo": "Aprenda python em 12 dias",
            "dataLancamento": "01/01/2012",
            "numeroPaginas": 100
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 20,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 7,
    "last": true,
    "size": 20,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "numberOfElements": 7,
    "first": true,
    "empty": false
}
```

- <strong>POST localhost:8080/livros</strong> --> cadastra um novo livro.
```json
{
    "titulo": "Aprenda python em 12 dias",
    "dataLancamento": "01/01/2012",
    "numeroPaginas": 100,
    "autorId": 1
}
```

- <strong>GET localhost:8080/relatorios/quantidade-livros-autor</strong> --> retorna a quantidade de livros de cada autor.
```json
[
    {
        "autor": "André da Silva",
        "quantidadeLivros": 2,
        "percentual": 0.285714285
    },
    {
        "autor": "Fernanda Nogueira",
        "quantidadeLivros": 2,
        "percentual": 0.285714285
    },
    {
        "autor": "Juliana Carvalho",
        "quantidadeLivros": 1,
        "percentual": 0.142857142
    },
    {
        "autor": "Rita de Assis",
        "quantidadeLivros": 1,
        "percentual": 0.142857142
    },
    {
        "autor": "Rodrigo de Souza",
        "quantidadeLivros": 1,
        "percentual": 0.142857142
    }
]
```

<a name="licenca"/></a>
### :copyright: Licença

Este projeto está licenciado nos termos da licença MIT.




