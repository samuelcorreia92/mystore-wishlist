# MyStore | Wishlist Service
Essa é uma POC de um serviço de wishlist (Lista de Desejos) que faz parte de um ecossistema de um e-commerce baseado em microsserviços.
As principais tecnologias utilizadas foram as seguintes:

    - Java 17
    - SpringBoot 3.1.0
    - Maven 3
    - MongoDB 6.0.6
    - RabbitMQ (In Progress)


#### Build and Run
Realizar o `build` e `package` do projeto utilizando o maven:
 ```shell
mvn clean package -Dmaven.test.skip=true
```

Agora basta subir o projeto utilizando o docker-compose (`docker/docker-compose.yaml`):
```shell
docker compose -f docker/docker-compose.yaml up
```
