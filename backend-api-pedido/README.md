# Sistema de Pedidos API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

O projeto é uma API construída usando **Java 17, Java Spring, MYSQL e H2 as the database.** 


## Índice

- [Instalação](#installation)
- [Configuration](#configuration)
- [Cliente](#usage)
- [Pedido](#usage)
- [ItensPedido](#usage)
- [API Endpoints](#api-endpoints)
- [Banco de dados](#database)
- [Contributing](#contributing)

## Instalação

1. Clone the repository:

```bash
git clone https://github.com/sidinelson/Sistema-Pedidos.git
```

2. Instalar dependências no Maven

## Uso

1. Inicie o aplicativo com Maven
2. A API estará acessível em http://localhost:8080


## API Endpoints
A API fornece os seguintes endpoints:

**GET CLIENTE**
```markdown
GET / cliente - Recuperar uma lista de todos os Clientes.
```
```json
[
  {
    "id": 1,
    "nome": "João Paulo"
  },
  {
    "id": 2,
    "nome": "Maria Braga"
  },
  {
    "id": 3,
    "nome": "Ana Paula"
  },
  {
    "id": 4,
    "nome": "Luiz Pereira"
  },
  {
    "id": 5,
    "nome": "Sidicley Bartolomeu"
  },
  {
    "id": 6,
    "nome": "Isabella Roberta"
  },
  {
    "id": 7,
    "nome": "Pedro Ferreira"
  },
  {
    "id": 8,
    "nome": "Jorge Ferreira"
  },
  {
    "id": 9,
    "nome": "Julio Silva"
  },
  {
    "id": 10,
    "nome": "Jadecira Pereira"
  }
]

```
```xml

<List>
    <item>
        <id>1</id>
        <nome>João Paulo</nome>
    </item>
    <item>
        <id>2</id>
        <nome>Maria Braga</nome>
    </item>
    <item>
        <id>3</id>
        <nome>Ana Paula</nome>
    </item>
    <item>
        <id>4</id>
        <nome>Luiz Pereira</nome>
    </item>
    <item>
        <id>5</id>
        <nome>Sidicley Bartolomeu</nome>
    </item>
    <item>
        <id>6</id>
        <nome>Isabella Roberta</nome>
    </item>
    <item>
        <id>7</id>
        <nome>Pedro Ferreira</nome>
    </item>
    <item>
        <id>8</id>
        <nome>Jorge Ferreira</nome>
    </item>
    <item>
        <id>9</id>
        <nome>Julio Silva</nome>
    </item>
    <item>
        <id>10</id>
        <nome>Jadecira Pereira</nome>
    </item>
</List>



```

**POST CLIENTE**
```markdown
POST / cliente - Cadastre um novo Cliente no App
```
```json
{
  "nome":"Jadecira Pereira"
}
```
```xml
<ClienteRequest>
    <nome>Jadecira Pereira</nome>
</ClienteRequest>

```


**GET PEDIDO**
```markdown
GET / pedido - Recuperar uma lista de todos os pedidos por filtro [Número do Pedido, Data Cadastro do Pedido, Um Filtro Todos].
```
```json
[
  {
    "numeroPedido": 1,
    "valorTotal": 1220.0,
    "idItensPedido": 1,
    "numeroControle": 456,
    "dataCadastro": "2024-06-11",
    "nome": "Vidro",
    "valor": 320.0,
    "quantidade": 1,
    "clientenome": "Jadecira Pereira"
  },
  {
    "numeroPedido": 1,
    "valorTotal": 1220.0,
    "idItensPedido": 2,
    "numeroControle": 123,
    "dataCadastro": "2024-06-11",
    "nome": "Papel",
    "valor": 400.0,
    "quantidade": 1,
    "clientenome": "Jadecira Pereira"
  },
  {
    "numeroPedido": 1,
    "valorTotal": 1220.0,
    "idItensPedido": 3,
    "numeroControle": 1789,
    "dataCadastro": "2024-06-11",
    "nome": "Livro",
    "valor": 500.0,
    "quantidade": 1,
    "clientenome": "Jadecira Pereira"
  },
  {
    "numeroPedido": 2,
    "valorTotal": 13500.0,
    "idItensPedido": 52,
    "numeroControle": 1789,
    "dataCadastro": "2024-06-11",
    "nome": "Mesa",
    "valor": 1000.0,
    "quantidade": 1,
    "clientenome": "Jadecira Pereira"
  },
  {
    "numeroPedido": 2,
    "valorTotal": 13500.0,
    "idItensPedido": 53,
    "numeroControle": 789,
    "dataCadastro": "2024-06-11",
    "nome": "Cadeira",
    "valor": 500.0,
    "quantidade": 3,
    "clientenome": "Jadecira Pereira"
  },
  {
    "numeroPedido": 2,
    "valorTotal": 13500.0,
    "idItensPedido": 54,
    "numeroControle": 222,
    "dataCadastro": "2024-06-11",
    "nome": "Cama",
    "valor": 4000.0,
    "quantidade": 1,
    "clientenome": "Sidicley Bartolomeu"
  },
  {
    "numeroPedido": 2,
    "valorTotal": 13500.0,
    "idItensPedido": 55,
    "numeroControle": 3322,
    "dataCadastro": "2024-06-11",
    "nome": "TV LG",
    "valor": 5000.0,
    "quantidade": 1,
    "clientenome": "Sidicley Bartolomeu"
  },
  {
    "numeroPedido": 2,
    "valorTotal": 13500.0,
    "idItensPedido": 56,
    "numeroControle": 555,
    "dataCadastro": "2024-06-11",
    "nome": "SOFA",
    "valor": 3000.0,
    "quantidade": 1,
    "clientenome": "Sidicley Bartolomeu"
  }
]

```



```xml
<List>
    <item>
        <numeroPedido>2</numeroPedido>
        <valorTotal>13500.0</valorTotal>
        <idItensPedido>52</idItensPedido>
        <numeroControle>1789</numeroControle>
        <dataCadastro>2024-06-11</dataCadastro>
        <nome>Mesa</nome>
        <valor>1000.0</valor>
        <quantidade>1</quantidade>
        <clientenome>Jadecira Pereira</clientenome>
    </item>
    <item>
        <numeroPedido>2</numeroPedido>
        <valorTotal>13500.0</valorTotal>
        <idItensPedido>53</idItensPedido>
        <numeroControle>789</numeroControle>
        <dataCadastro>2024-06-11</dataCadastro>
        <nome>Cadeira</nome>
        <valor>500.0</valor>
        <quantidade>3</quantidade>
        <clientenome>Jadecira Pereira</clientenome>
    </item>
    <item>
        <numeroPedido>2</numeroPedido>
        <valorTotal>13500.0</valorTotal>
        <idItensPedido>54</idItensPedido>
        <numeroControle>222</numeroControle>
        <dataCadastro>2024-06-11</dataCadastro>
        <nome>Cama</nome>
        <valor>4000.0</valor>
        <quantidade>1</quantidade>
        <clientenome>Sidicley Bartolomeu</clientenome>
    </item>
    <item>
        <numeroPedido>2</numeroPedido>
        <valorTotal>13500.0</valorTotal>
        <idItensPedido>55</idItensPedido>
        <numeroControle>3322</numeroControle>
        <dataCadastro>2024-06-11</dataCadastro>
        <nome>TV LG</nome>
        <valor>5000.0</valor>
        <quantidade>1</quantidade>
        <clientenome>Sidicley Bartolomeu</clientenome>
    </item>
    <item>
        <numeroPedido>2</numeroPedido>
        <valorTotal>13500.0</valorTotal>
        <idItensPedido>56</idItensPedido>
        <numeroControle>555</numeroControle>
        <dataCadastro>2024-06-11</dataCadastro>
        <nome>SOFA</nome>
        <valor>3000.0</valor>
        <quantidade>1</quantidade>
        <clientenome>Sidicley Bartolomeu</clientenome>
    </item>
</List>


```

**POST PEDIDO**
```markdown
POST /pedido - Cadastre um novo pedido no App
```
```json
{
  "dataCadastro": "2024-06-12"
}
```

```xml
<PedidoRequest>
    <dataCadastro>2024-06-12</dataCadastro>
</PedidoRequest>

```



**POST ITENS DO PEDIDO**
```markdown
POST /Itens do Pedido - Cadastre um novo pedido no App
```
```json
{
  "numeroControle": 555,
  "numeroPedido":2,
  "dataCadastro": "2024-06-11",
  "nome": "SOFA",
  "valor": "3000",
  "quantidade": 1,
  "idcliente": 5
}
```

```xml
<ItensPedidoRequest>        
    <numeroPedido>2</numeroPedido>
    <numeroControle>9192</numeroControle>
    <dataCadastro></dataCadastro>
    <nome>CAMISA</nome>
    <valor>100</valor>
    <quantidade>1</quantidade>
    <idcliente>9</idcliente>
</ItensPedidoRequest>
```



**GET CALCULAR PEDIDO**
```json

{
  "status": 200,
  "message": "PedidoModel calculado com sucesso! R$ 1220.0"
}


```

```xml

<SuccessResponse>
    <status>200</status>
    <message>Pedido calculado com sucesso! R$ 13500.0</message>
</SuccessResponse>


```

## Banco de Dados
O Projeto Utilizar
[MYSQL Database](https://www.mysql.com/downloads/) e ( [H2 Database](https://www.h2database.com/html/tutorial.html) para teste unitário ) em as the database. 




