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
POST /pedido - Cadastre um novo pedido no App já com os itens do pedido
```
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

**GET CONSULTAR PEDIDO E PRODUTO DOS ITENS DO PEDIDO**
```markdown
GET /api/pedido/consultarPedido/ - Consultar pedido no App
```
```json
{
    "numeroOrder": 15,
    "numeroPedido": 22899,
    "dataCadastro": "2025-02-09",
    "situacao": "PROCESSANDO",
    "descontoTotal": 100.00,
    "valorTotal": 18922.00,
    "itens": [
        {
            "idItensPedido": 9,
            "numeroPedido": 22899,
            "dataCadastro": "2025-02-09",
            "codigoProduto": "521",
            "descricaoProduto": "Chapel",
            "quantidade": 5,
            "desconto": 0.00,
            "preco": 8.40,
            "valorTotal": 42.00
        },
        {
            "idItensPedido": 10,
            "numeroPedido": 22899,
            "dataCadastro": "2025-02-09",
            "codigoProduto": "456",
            "descricaoProduto": "Celular Iphone",
            "quantidade": 1,
            "desconto": 520.00,
            "preco": 8500.00,
            "valorTotal": 7980.00
        },
        {
            "idItensPedido": 11,
            "numeroPedido": 22899,
            "dataCadastro": "2025-02-09",
            "codigoProduto": "562",
            "descricaoProduto": "Celular Galaxy",
            "quantidade": 1,
            "desconto": 1000.00,
            "preco": 11000.00,
            "valorTotal": 10000.00
        },
        {
            "idItensPedido": 12,
            "numeroPedido": 22899,
            "dataCadastro": "2025-02-09",
            "codigoProduto": "377",
            "descricaoProduto": "PUMA",
            "quantidade": 1,
            "desconto": 100.00,
            "preco": 1000.00,
            "valorTotal": 900.00
        }
    ]
}
```

## Banco de Dados
O Projeto Utilizar
[MYSQL Database](https://www.mysql.com/downloads/) e ( [H2 Database](https://www.h2database.com/html/tutorial.html) para teste unitário ) em as the database. 




