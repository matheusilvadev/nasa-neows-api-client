# nasa-neows-client (ou o nome que você escolheu)

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](https://...) [![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE) 
## Descrição

Este projeto é uma API cliente construída com Spring Boot e WebFlux para interagir com a [API neoWs (Near Earth Objects Web Service)](https://api.nasa.gov/neo/rest/v1/) da NASA. Ele oferece funcionalidades para buscar informações sobre asteroides próximos à Terra, incluindo feeds por data, pesquisa por ID de corpo pequeno e navegação paginada pelo catálogo completo de asteroides.

## Funcionalidades Principais

* **Neo - Feed:** Recupera uma lista de asteroides com base no período de maior aproximação da Terra (datas de início e fim).
* **Neo - Lookup:** Busca informações detalhadas de um asteroide específico utilizando seu ID da NASA JPL (SPK-ID).
* **Neo - Browse:** Permite navegar pelo conjunto geral de dados de asteroides com suporte à paginação para lidar com grandes volumes de dados.

## Tecnologias Utilizadas

* [Java](https://www.java.com/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring WebFlux](https://spring.io/projects/spring-webflux) (para programação reativa e não bloqueante)
* [WebClient](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-client) (cliente HTTP reativo do Spring)
* [Jackson](https://github.com/FasterXML/jackson) (para serialização/desserialização JSON)
* [Maven](https://maven.apache.org/) (gerenciamento de dependências e build)

## Pré-requisitos

* [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) instalado.
* [Maven](https://maven.apache.org/download.cgi.html) ou [Gradle](https://gradle.org/install/) instalado (dependendo do seu sistema de build).
* Uma [chave de API da NASA](https://api.nasa.gov/) (você precisará se registrar para obter uma chave gratuita).

## Configuração

1.  **Clone o repositório:**
    ```bash
    git clone [URL_DO_SEU_REPOSITORIO]
    cd [NOME_DO_SEU_REPOSITORIO]
    ```

2.  **Configure a chave de API e a URL base:**
    Crie ou edite o arquivo `src/main/resources/application.properties` (ou `application.yml`) e adicione sua chave de API da NASA e a URL base da API neoWs:
    ```properties
    api.key=SUA_CHAVE_DE_API
    nasa.api.base-url=[https://api.nasa.gov/neo/rest/v1/](https://api.nasa.gov/neo/rest/v1/)
    ```
    Substitua `SUA_CHAVE_DE_API` pela sua chave real.

3.  **Build do projeto:**
    Se você estiver usando Maven:
    ```bash
    mvn clean install
    ```
    Se você estiver usando Gradle:
    ```bash
    ./gradlew clean build
    ```

## Como Executar a Aplicação

Após o build bem-sucedido, você pode executar a aplicação Spring Boot:

Se você estiver usando Maven:
```bash
mvn spring-boot:run
