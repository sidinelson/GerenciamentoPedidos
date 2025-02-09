package br.com.pedidos.dto;

import br.com.pedidos.model.ItensPedidoModel;
import br.com.pedidos.model.PedidoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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



}
