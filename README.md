# Backend BP API

Este proyecto consiste en una aplicación backend desarrollada en Java utilizando Spring Boot. La aplicación está diseñada para gestionar clientes, cuentas bancarias, y los movimientos asociados a estas cuentas. La solución está compuesta por dos microservicios, uno para la gestión de **Clientes/Personas** y otro para **Cuentas/Movimientos**, y utiliza RabbitMQ para la comunicación asincrónica entre ellos.

## Tecnologías Utilizadas

- **Java** (Java 17 recomendado)
- **Spring Boot** (Spring Web, Spring Data JPA, Spring AMQP para RabbitMQ)
- **H2 Database** (base de datos en memoria para desarrollo y pruebas)
- **RabbitMQ** (para comunicación asincrónica)
- **Docker** (para despliegue de contenedores)
- **Postman** (para validación de endpoints)

## Requisitos Previos

1. **JDK 17**: Asegúrate de tener Java Development Kit (JDK) 17 instalado.
2. **Maven o Gradle**: Herramienta de construcción (se utiliza Gradle en este proyecto).
3. **Docker**: Instalado y ejecutándose para el despliegue de RabbitMQ y la aplicación.
4. **RabbitMQ**: Instalado localmente o ejecutado en un contenedor Docker.

## Configuración del Entorno

### 1. Instalar Dependencias

Antes de ejecutar la aplicación, asegúrate de instalar las dependencias necesarias. Ejecuta el siguiente comando en la raíz del proyecto:

```bash
./gradlew build
