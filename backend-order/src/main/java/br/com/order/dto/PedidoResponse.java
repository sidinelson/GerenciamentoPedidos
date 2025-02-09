package br.com.order.dto;

import br.com.order.model.ItensPedidoModel;
import br.com.order.model.PedidoModel;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class PedidoResponse {

    private Long numeroPedido;
    private LocalDate dataCadastro;
    private String situacao;
    private BigDecimal descontoTotal;
    private BigDecimal valorTotal;

    List<ItensPedidoModel> itens;

    public static PedidoResponse of(PedidoModel pedido){
        var response = new PedidoResponse();
        BeanUtils.copyProperties(pedido,response);
        return response;
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

    public List<ItensPedidoModel> getItens() {
        return itens;
    }

    public void setItens(List<ItensPedidoModel> itens) {
        this.itens = itens;
    }
}
