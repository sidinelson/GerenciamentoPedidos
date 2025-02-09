# Sistema de Pedidos API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

O projeto é uma API construída usando **Java 17, Java Spring, RabbitMq, MYSQL the database.** 


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


**GET PEDIDO**
```markdown
GET / pedido - Recuperar uma lista de todos os pedidos por filtro [Número do Pedido, Data Cadastro do Pedido, Um Filtro Todos].
```
```json
[
 {
    "numeroPedido": "12225",
    "situacao": "PROCESSANDO",
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



**POST PEDIDO**
```markdown
POST /pedido - Cadastre um novo pedido no App
```
```json
{
    "numeroPedido": "13228",
    "situacao": "PROCESSANDO"
}
```




**POST ITENS DO PEDIDO**
```markdown
POST /Itens do Pedido - Cadastre um novo pedido no App
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


http://localhost:8080/api/pedido/fercharPedido/22225
**GET FECHAR PEDIDO**
```
Pedido fechado com sucesso...


```



## Banco de Dados
O Projeto Utilizar
[MYSQL Database](https://www.mysql.com/downloads/) e ( [H2 Database](https://www.h2database.com/html/tutorial.html) para teste unitário ) em as the database. 




