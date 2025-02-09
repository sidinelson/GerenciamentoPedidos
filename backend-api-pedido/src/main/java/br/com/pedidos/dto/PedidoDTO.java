package br.com.pedidos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private List<ItensPedidoDTO> itens;


}
