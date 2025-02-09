package br.com.pedidos.infra;

import br.com.pedidos.dto.ItensPedidoDto;
import br.com.pedidos.model.ItensPedidoModel;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoConverter {

    public ItensPedidoDto converterParaDTO(ItensPedidoModel itensPedidoModel) {

        // Converte de ItemPedido para ItemPedidoDTO

        if (itensPedidoModel == null) {
            return null;
        }

        return new ItensPedidoDto(
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

    // Converte de PedidoDto para Pedido (entidade)
    public ItensPedidoModel toEntity(ItensPedidoDto itensPedidoDTO) {

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
