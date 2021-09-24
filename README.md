# :computer: livraria-api
API REST com Spring Boot para um sistema de gestão de uma livraria online.

Este projeto está sendo desenvolvido ao longo do Bootcamp Java Alura 2021.

### :hourglass: Status do projeto
Em desenvolvimento.

### :hammer_and_wrench: Tecnologias

As seguintes ferramentas são utilizadas no desenvolvimento deste projeto:

- [Java](https://www.oracle.com/java/)
- [Eclipse IDE](https://www.eclipse.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [Lombok](https://projectlombok.org/)
- [ModelMapper](http://modelmapper.org/)
- [Git](https://git-scm.com/)
- [Docker](http://modelmapper.org/)

### :page_with_curl: Features
- [x] Cadastro de autores
- [x] Cadastro de livros
- [x] Listagem de autores
- [x] Listagem de livros

### :pencil: Pré-requisitos

Antes de começar, você precisa ter instalado em sua máquina as seguintes ferramentas:
- [Git](https://git-scm.com/)
- [Docker](https://www.docker.com/)

Além disso, é recomendado ter uma ferramenta para testar a API, como, por exemplo, o [Postman](https://www.postman.com/).

### :rocket: Executando a aplicação

```bash
# Clone este repositório
$ git clone <https://github.com/antonioeloy/livraria-api.git>

# Crie a imagem da aplicação
$ docker image build -t livraria-api .

# Execute o container da aplicação
$ docker run -d -p8080:8080 livraria-api

# A aplicação iniciará na porta 8080
```
### :copyright: Licença

Este projeto está licenciado nos termos da licença MIT.




