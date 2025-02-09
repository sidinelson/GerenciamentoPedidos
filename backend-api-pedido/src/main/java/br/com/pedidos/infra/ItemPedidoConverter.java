package br.com.pedidos.infra;

import br.com.pedidos.dto.ItensPedidoDTO;
import br.com.pedidos.model.ItensPedidoModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemPedidoConverter {

    public ItensPedidoDTO converterParaDTO(ItensPedidoModel itensPedidoModel) {

        // Converte de ItemPedido para ItemPedidoDTO

        if (itensPedidoModel == null) {
            return null;
        }

        return new ItensPedidoDTO(
                itensPedidoModel.getIdItensPedido(),
                itensPedidoModel.getPedido(),
                itensPedidoModel.getNumeroPedido(),
                itensPedidoModel.getDataCadastro(),
                itensPedidoModel.getCodigoProduto(),
                itensPedidoModel.getDescricaoProduto(),
                itensPedidoModel.getQuantidade(),
                itensPedidoModel.getDesconto(),
                itensPedidoModel.getPreco(),
                itensPedidoModel.getValorTotal()
        );
    }

    // Converte de PedidoDTO para Pedido (entidade)
    public ItensPedidoModel toEntity(ItensPedidoDTO itensPedidoDTO) {

        if (itensPedidoDTO == null) {
            return null;
        }

        ItensPedidoModel model = new ItensPedidoModel();
        model.setIdItensPedido(itensPedidoDTO.getIdItensPedido());
        model.setNumeroPedido(itensPedidoDTO.getNumeroPedido()); // Número do pedido
        model.setCodigoProduto(itensPedidoDTO.getCodigoProduto());
        model.setDescricaoProduto(itensPedidoDTO.getDescricaoProduto());
        model.setQuantidade(itensPedidoDTO.getQuantidade());
        model.setDesconto(itensPedidoDTO.getDesconto());
        model.setPreco(itensPedidoDTO.getPreco());
        model.setValorTotal(itensPedidoDTO.getValorTotal());
        return model;
    }

}
