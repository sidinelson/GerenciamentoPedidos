package br.com.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ITENS_PEDIDO")
public class ItensPedidoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ITENS_ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItensPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NUMERO_ORDER")
    @JsonIgnore // Evita a recursão infinita
    private PedidoModel pedido;

    @Column(name = "NUMERO_PEDIDO")
    private Long numeroPedido;

    @Column(name = "DATA_CADASTRO")
    private LocalDate dataCadastro = LocalDate.now();

    @Column(name = "PRODUTO", nullable = false)
    private String codigoProduto;

    @Column(name = "DESCRICAO_PRODUTO", nullable = false)
    private String descricaoProduto;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @Column(name = "DESCONTO")
    private BigDecimal desconto;

    @Column(name = "PRECO")
    private BigDecimal preco;

    @Column(name = "TOTAL")
    private BigDecimal valorTotal;

    public Long getIdItensPedido() {
        return idItensPedido;
    }

    public void setIdItensPedido(Long idItensPedido) {
        this.idItensPedido = idItensPedido;
    }

    public PedidoModel getPedido() {
        return pedido;
    }

    public void setPedido(PedidoModel pedido) {
        this.pedido = pedido;
    }

    public Long getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Long numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
