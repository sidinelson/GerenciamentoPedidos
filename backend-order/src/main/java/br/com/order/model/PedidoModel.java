package br.com.order.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PEDIDO")
public class PedidoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroOrder;

    @Column(name = "NUMERO_PEDIDO", unique = true, nullable = false)
    private Long numeroPedido;

    @Column(name = "DATA_CADASTRO", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate dataCadastro;

    @Column(name = "SITUACAO")
    private String situacao;

    @Column(name = "DESCONTO_TOTAL")
    private BigDecimal descontoTotal;

    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItensPedidoModel> itens;

    public Long getNumeroOrder() {
        return numeroOrder;
    }

    public Long getNumeroPedido() {
        return numeroPedido;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public String getSituacao() {
        return situacao;
    }

    public BigDecimal getDescontoTotal() {
        return descontoTotal;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public List<ItensPedidoModel> getItens() {
        return itens;
    }

    public void setNumeroOrder(Long numeroOrder) {
        this.numeroOrder = numeroOrder;
    }

    public void setNumeroPedido(Long numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public void setDescontoTotal(BigDecimal descontoTotal) {
        this.descontoTotal = descontoTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setItens(List<ItensPedidoModel> itens) {
        this.itens = itens;
    }
}