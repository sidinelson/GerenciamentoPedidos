# Sistema de Order Pedidos

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)
## 📌 Sobre o Projeto
O **backend-order** é um microserviço desenvolvido em **Java 17** com **Spring Boot**, responsável pelo processamento de pedidos e itens de pedido. Ele recebe pedidos de um sistema externo **A**, processa os valores e envia os dados processados para um sistema externo **B**.

Além disso, ele disponibiliza uma API segura para fechar pedidos e cadastro de produtos, garantindo escalabilidade e alta disponibilidade através do uso de **RabbitMQ** para mensageria assíncrona.

## 🛠️ Tecnologias Utilizadas
- **Java 17**
- **Spring Boot**
- **Spring JPA**
- **RabbitMQ**
- **Maven 3.4.2**
  
## 📂 Estrutura do Projeto
A estrutura do projeto segue a organização por camadas.



## 🚀 Funcionalidades Principais
✔️ **CRUD de Pedidos, Itens de Pedido e Produtos**  
✔️ **Processamento assíncrono de pedidos com RabbitMQ**  
✔️ **Cálculo automático do valor total do pedido**  

## 🔧 Configuração e Execução

### 🔹 Pré-requisitos:
Antes de rodar o projeto, você precisa ter instalado:
- **JDK 17**
- **Maven 3.4.2**
- **Docker** (Opcional para subir um ambiente com MySQL e RabbitMQ)

### 🔹 Passos para Rodar
1. Clone o repositório:
   ```sh
   git clone https://github.com/seuusuario/backend-order.git
   cd backend-order






