package br.com.order.dto;

import br.com.order.model.PedidoModel;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItensPedidoDto {

    @JsonProperty("idItensPedido")
    private Long idItensPedido;
    @JsonProperty("pedido")
    private PedidoDto pedido;
    @JsonProperty("numeroPedido")
    private Long numeroPedido;
    private LocalDate dataCadastro;
    @JsonProperty("codigoProduto")
    private String codigoProduto;
    @JsonProperty("descricaoProduto")
    private String descricaoProduto;
    @JsonProperty("quantidade")
    private Integer quantidade;
    @JsonProperty("desconto")
    private BigDecimal desconto;
    @JsonProperty("preco")
    private BigDecimal preco;
    @JsonProperty("valorTotal")
    private BigDecimal valorTotal;

    public ItensPedidoDto(Long idItensPedido, PedidoDto pedido, Long numeroPedido, LocalDate dataCadastro, String codigoProduto, String descricaoProduto, Integer quantidade, BigDecimal desconto, BigDecimal preco, BigDecimal valorTotal) {
        this.idItensPedido = idItensPedido;
        this.pedido = pedido;
        this.numeroPedido = numeroPedido;
        this.dataCadastro = dataCadastro;
        this.codigoProduto = codigoProduto;
        this.descricaoProduto = descricaoProduto;
        this.quantidade = quantidade;
        this.desconto = desconto;
        this.preco = preco;
        this.valorTotal = valorTotal;
    }

    public ItensPedidoDto() {

    }

    public ItensPedidoDto(Long idItensPedido, PedidoModel pedido, Long numeroPedido, LocalDate dataCadastro, String codigoProduto, String descricaoProduto, Integer quantidade, BigDecimal desconto, BigDecimal preco, BigDecimal valorTotal) {
    }


    public Long getIdItensPedido() {
        return idItensPedido;
    }

    public void setIdItensPedido(Long idItensPedido) {
        this.idItensPedido = idItensPedido;
    }

    public PedidoDto getPedido() {
        return pedido;
    }

    public void setPedido(PedidoDto pedido) {
        this.pedido = pedido;
    }

    public Long getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Long numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public LocalDate getDataCadastro() {return dataCadastro; }

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
