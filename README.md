# MyStore | Wishlist Service
Essa é uma POC de um serviço de wishlist (Lista de Desejos) que faz parte de um ecossistema de um e-commerce baseado em microsserviços.
As principais tecnologias utilizadas foram as seguintes:

    - Java 17
    - SpringBoot 3.1.0
    - MongoDB 6.0.6
    - RabbitMQ (In Progress)


#### Build: Maven
Realizar o `build` e `package` do projeto no computador local:
 ```shell
mvn clean package -Dmaven.test.skip=true
```

#### Build: Docker
Gerar uma nova imagem docker baseada no código-fonte atual

 ```shell
docker build . -t samuelferd/mystore-wishlist:0.0.1 -f docker/Dockerfile
```

#### Build: Docker ( Push Image ) [ OPCIONAL ]
Nessa etapa vamos subir a nova imagem no Docker Registry (nesse caso será utilizado o registry oficial do Docker).

 ```shell
docker push samuelferd/mystore-wishlist:0.0.1
```

#### Run: Utilizando a imagem Docker ( Docker-compose )
Para subir o projeto numa instância Docker local basta executar o docker-compose (`docker/docker-compose.yaml`):

```shell
docker compose -f docker/docker-compose.yaml up
```
