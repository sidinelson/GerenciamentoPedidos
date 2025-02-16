# Sistema de Pedidos API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

O projeto é uma API construída usando **Java 17, Java Spring, Spring Security, RabbitMq, MYSQL the database.**


## Índice

- [Instalação](#installation)
- [Configuration](#configuration)
- [Produto Externo B](#usage)
- [Pedido](#usage)
- [ItensPedido](#usage)
- [API Endpoints](#api-endpoints)
- [Banco de dados](#database)
- [Contributing](#contributing)

## Instalação

1. Clone the repository:

```bash
git clone https://github.com/sidinelson/GerenciamentoPedidos.git
```

2. Instalar dependências no Maven

## Uso

1. Inicie o aplicativo com Maven
2. A API estará acessível em http://localhost:8080/api/pedido/


## API Endpoints
A API fornece os seguintes endpoints:


**POST PEDIDO**
```markdown
POST - /api/pedido

*Pedido - Cadastre um novo pedido no App já com os itens do pedido
```
```
```json
[
 {
    "numeroPedido": "12225",
    "itens": [
        {
            "numeroPedido":"22225",
            "codigoProduto":"521",
            "descricaoProduto":"Chapel",
            "quantidade": 5,
            "desconto":0.00,
            "preco":8.40
        },
        {
            "numeroPedido":"22225",
            "codigoProduto":"456",
            "descricaoProduto":"Celular Iphone",
            "quantidade": 1,
            "desconto":520.00,
            "preco":8500.00
        },
        {
            "numeroPedido":"22225",
            "codigoProduto":"562",
            "descricaoProduto":"Celular Galaxy",
            "quantidade": 1,
            "desconto":1000.00,
            "preco":11000.00
        }
    ]
}
]
```
**PUT ITENS DO PEDIDO**
```markdown
PUT - /api/itenspedido

Cadastre um novo Itens de Pedido no App
```
```json
{
  "numeroPedido":"13228",
  "codigoProduto":"377",
  "descricaoProduto":"PUMA",
  "quantidade": 1,
  "desconto":100.00,
  "preco":1000.00
}
```




**PUT FECHAR PEDIDO**

```markdown
PUT - /api/pedido/fercharPedido

Fechar um Pedido no App
```
Pedido fechado com sucesso...
```json
{
  "numeroPedido":"13228"
}
```

```
```
**GET CONSULTAR PEDIDO E ITEM DO PEDIDO**

```markdown
GET /api/pedido/consultarPedido/22899

Consultar Pedido no App
```
```json
{
  "numeroOrder": 22,
  "numeroPedido": 50007,
  "dataCadastro": "2025-02-15",
  "situacao": "PROCESSANDO",
  "descontoTotal": 1620.00,
  "valorTotal": 20322.00,
  "itens": [
    {
      "idItensPedido": 39,
      "numeroPedido": 50007,
      "dataCadastro": "2025-02-15",
      "codigoProduto": "521",
      "descricaoProduto": "Chapel",
      "quantidade": 5,
      "desconto": 0.00,
      "preco": 8.40,
      "valorTotal": 42.00
    },
    {
      "idItensPedido": 40,
      "numeroPedido": 50007,
      "dataCadastro": "2025-02-15",
      "codigoProduto": "456",
      "descricaoProduto": "Celular Iphone",
      "quantidade": 1,
      "desconto": 520.00,
      "preco": 8500.00,
      "valorTotal": 7980.00
    },
    {
      "idItensPedido": 41,
      "numeroPedido": 50007,
      "dataCadastro": "2025-02-15",
      "codigoProduto": "562",
      "descricaoProduto": "Celular Galaxy",
      "quantidade": 1,
      "desconto": 1000.00,
      "preco": 11000.00,
      "valorTotal": 10000.00
    },
    {
      "idItensPedido": 42,
      "numeroPedido": 50007,
      "dataCadastro": "2025-02-15",
      "codigoProduto": "111",
      "descricaoProduto": "Camisa Polo",
      "quantidade": 1,
      "desconto": 0.00,
      "preco": 300.00,
      "valorTotal": 300.00
    },
    {
      "idItensPedido": 44,
      "numeroPedido": 50007,
      "dataCadastro": "2025-02-15",
      "codigoProduto": "377",
      "descricaoProduto": "PUMA",
      "quantidade": 1,
      "desconto": 100.00,
      "preco": 2100.00,
      "valorTotal": 2000.00
    }
  ]
}
```

## Banco de Dados
O Projeto Utilizar
[MYSQL Database](https://www.mysql.com/downloads/) e ( [H2 Database](https://www.h2database.com/html/tutorial.html) para teste unitário ) em as the database. 




