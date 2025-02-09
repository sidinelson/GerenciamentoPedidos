package br.com.pedidos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.util.ObjectUtils.isEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "ITENS_PEDIDO")
public class ItensPedidoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "ITENS_ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItensPedido ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NUMERO_ORDER")
    @JsonIgnore // Evita a recursão infinita
    private PedidoModel pedido;

    @Column(name = "NUMERO_PEDIDO")
    private Long numeroPedido;

    @Column(name = "DATA_CADASTRO")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate dataCadastro;

    @Column(name = "PRODUTO", nullable = false)
    private String  codigoProduto;

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


}
