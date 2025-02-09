package br.com.order.dto;

import br.com.order.model.ItensPedidoModel;
import br.com.order.model.PedidoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;


public class ItemPedidoResponse {

    private Long idItensPedido;
    private PedidoModel numeroOrder;
    private Long numeroPedido;
    private LocalDate dataCadastro;
    private String codigoProduto;
    private String descricaoProduto;
    private Integer quantidade;
    private BigDecimal desconto;
    private BigDecimal preco;
    private BigDecimal valorTotal;

    public static ItemPedidoResponse of(ItensPedidoModel itemPedido) {
        var response = new ItemPedidoResponse();
        BeanUtils.copyProperties(itemPedido, response);
        response.setNumeroOrder(itemPedido.getPedido());

        return response;
    }

    public Long getIdItensPedido() {
        return idItensPedido;
    }

    public void setIdItensPedido(Long idItensPedido) {
        this.idItensPedido = idItensPedido;
    }

    public PedidoModel getNumeroOrder() {
        return numeroOrder;
    }

    public void setNumeroOrder(PedidoModel numeroOrder) {
        this.numeroOrder = numeroOrder;
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
