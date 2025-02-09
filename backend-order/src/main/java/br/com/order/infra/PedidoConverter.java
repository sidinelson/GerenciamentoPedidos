package br.com.order.infra;

import br.com.order.dto.ItensPedidoDto;
import br.com.order.dto.PedidoDto;
import br.com.order.model.ItensPedidoModel;
import br.com.order.model.PedidoModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class PedidoConverter {

    public PedidoDto converterParaDTO(PedidoModel pedidoModel) {

        // Converte de Pedido para PedidoDto

        if (pedidoModel == null) {
            return null;
        }

        List<ItensPedidoDto> itensDTO = new ArrayList<>();

        for (ItensPedidoModel item : pedidoModel.getItens()) {
            ItensPedidoDto dto = new ItensPedidoDto();
            dto.setIdItensPedido(item.getIdItensPedido());
            dto.setNumeroPedido(item.getNumeroPedido()); // Número do pedido
            dto.setCodigoProduto(item.getCodigoProduto());
            dto.setDescricaoProduto(item.getDescricaoProduto());
            dto.setQuantidade(item.getQuantidade());
            dto.setDesconto(item.getDesconto());
            dto.setPreco(item.getPreco());
            dto.setValorTotal(item.getValorTotal());

            // Adiciona à lista de DTOs
            itensDTO.add(dto);
        }

        return new PedidoDto(
                pedidoModel.getNumeroOrder(),
                pedidoModel.getNumeroPedido(),
                pedidoModel.getDataCadastro(),
                pedidoModel.getSituacao(),
                pedidoModel.getDescontoTotal(),
                pedidoModel.getValorTotal(),
                itensDTO
        );
    }

    // Converte de PedidoDto para Pedido (entidade)
    public PedidoModel toEntity(PedidoDto pedidoDTO) {
        if (pedidoDTO == null) {
            return null;
        }

        List<ItensPedidoModel> itensModel = new ArrayList<>();
        if (!isEmpty(pedidoDTO.getItens())) {

            for (ItensPedidoDto item : pedidoDTO.getItens()) {
                ItensPedidoModel model = new ItensPedidoModel();
                model.setIdItensPedido(item.getIdItensPedido());
                model.setNumeroPedido(item.getNumeroPedido()); // Número do pedido
                model.setCodigoProduto(item.getCodigoProduto());
                model.setDescricaoProduto(item.getDescricaoProduto());
                model.setQuantidade(item.getQuantidade());
                model.setDesconto(item.getDesconto());
                model.setPreco(item.getPreco());
                model.setValorTotal(item.getValorTotal());

                // Adiciona à lista de DTOs
                itensModel.add(model);
            }
        }

        PedidoModel pedido = new PedidoModel();
        pedido.setNumeroOrder(pedidoDTO.getNumeroOrder());
        pedido.setNumeroPedido(pedidoDTO.getNumeroPedido());
        pedido.setDataCadastro(pedidoDTO.getDataCadastro());
        pedido.setSituacao(pedidoDTO.getSituacao());
        pedido.setDescontoTotal(pedidoDTO.getDescontoTotal());
        pedido.setValorTotal(pedidoDTO.getValorTotal());
        pedido.setItens(itensModel);
        return pedido;
    }

    // Método auxiliar para converter ItensPedidoDto para ItemPedido (entidade)
    private ItensPedidoModel toItemPedidoEntity(ItensPedidoDto dto) {
        ItensPedidoModel item = new ItensPedidoModel();
        item.setIdItensPedido(dto.getIdItensPedido());
        item.setNumeroPedido(dto.getNumeroPedido());
        item.setCodigoProduto(dto.getCodigoProduto());
        item.setDescricaoProduto(dto.getDescricaoProduto());
        item.setQuantidade(dto.getQuantidade());
        item.setDesconto(dto.getDesconto());
        item.setPreco(dto.getPreco());
        item.setValorTotal(dto.getValorTotal());
        return item;
    }
}

