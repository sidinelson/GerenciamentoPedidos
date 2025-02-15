package br.com.pedidos.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "NUMERO_PEDIDO", nullable = false)
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
    //@JsonManagedReference
    private List<ItensPedidoModel> itens;

    public void fecharPedido() {
        if ("FINALIZADO".equalsIgnoreCase(this.situacao)) {
            throw new RuntimeException("O pedido já está fechado.");
        }
        this.situacao = "FINALIZADO";
    }
}
