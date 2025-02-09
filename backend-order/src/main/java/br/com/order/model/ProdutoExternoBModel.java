package br.com.order.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name= "PROD_EXTERNO_B")
public class ProdutoExternoBModel {

    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "DATA_CADASTRO")
    private LocalDate dataCadastro;

    @Column(name = "PRODUTO", nullable = false)
    private String  produto;

    @Column(name = "DESCRICAO_PRODUTO", nullable = false)
    private String  descricaoProduto;

    @Column(name = "QUANTIDADE")
    private Integer  quantidade;

    @Column(name = "DESCONTO")
    private BigDecimal desconto;

    @Column(name = "PRECO")
    private BigDecimal preco;

    @Column(name = "TOTAL")
    private BigDecimal valorTotal;

    @Column(name = "NUMERO_PEDIDO")
    private BigDecimal numeroPedido;
}
