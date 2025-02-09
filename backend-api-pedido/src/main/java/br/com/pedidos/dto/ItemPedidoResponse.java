package br.com.pedidos.dto;

import br.com.pedidos.model.ItensPedidoModel;
import br.com.pedidos.model.PedidoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
