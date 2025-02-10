package br.com.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("numeroOrder")
    private Long numeroOrder;
    @JsonProperty("numeroPedido")
    private Long numeroPedido;
    @JsonProperty("dataCadastro")
    private LocalDate dataCadastro;
    @JsonProperty("situacao")
    private String situacao;
    @JsonProperty("descontoTotal")
    private BigDecimal descontoTotal;
    @JsonProperty("valorTotal")
    private BigDecimal valorTotal;
    @JsonProperty("itens")
    private List<ItensPedidoDto> itens;


    public PedidoDto(Long numeroOrder, Long numeroPedido, LocalDate dataCadastro, String situacao, BigDecimal descontoTotal, BigDecimal valorTotal, List<ItensPedidoDto> itens) {
        this.numeroOrder = numeroOrder;
        this.numeroPedido = numeroPedido;
        this.dataCadastro = dataCadastro;
        this.situacao = situacao;
        this.descontoTotal = descontoTotal;
        this.valorTotal = valorTotal;
        this.itens = itens;
    }

    public Long getNumeroOrder() {
        return numeroOrder;
    }

    public void setNumeroOrder(Long numeroOrder) {
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public BigDecimal getDescontoTotal() {
        return descontoTotal;
    }

    public void setDescontoTotal(BigDecimal descontoTotal) {
        this.descontoTotal = descontoTotal;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItensPedidoDto> getItens() {
        return itens;
    }

    public void setItens(List<ItensPedidoDto> itens) {
        this.itens = itens;
    }
}
