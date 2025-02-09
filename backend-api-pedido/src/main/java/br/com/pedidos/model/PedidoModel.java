package br.com.pedidos.model;


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
}
