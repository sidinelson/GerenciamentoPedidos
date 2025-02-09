package br.com.pedidos.infra;

import br.com.pedidos.dto.ItensPedidoDTO;
import br.com.pedidos.dto.PedidoDTO;
import br.com.pedidos.model.ItensPedidoModel;
import br.com.pedidos.model.PedidoModel;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoDTO converterParaDTO(PedidoModel pedidoModel) {

        // Converte de Pedido para PedidoDTO

        if (pedidoModel == null) {
            return null;
        }

        /*List<ItensPedidoDTO> itensDTO = pedidoModel.getItens().stream()
                .map(this::toItemPedidoDTO)
                .collect(Collectors.toList());*/
        List<ItensPedidoDTO> itensDTO = new ArrayList<>();

        for (ItensPedidoModel item : pedidoModel.getItens()) {
            ItensPedidoDTO  dto = new ItensPedidoDTO();
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

        return new PedidoDTO(
                pedidoModel.getNumeroPedido(),
                pedidoModel.getDataCadastro(),
                pedidoModel.getSituacao(),
                pedidoModel.getDescontoTotal(),
                pedidoModel.getValorTotal(),
                itensDTO
        );
    }

    // Converte de PedidoDTO para Pedido (entidade)
    public PedidoModel toEntity(PedidoDTO pedidoDTO) {
        if (pedidoDTO == null) {
            return null;
        }
        List<ItensPedidoModel> itens = null;
        if(!isEmpty(pedidoDTO.getItens())){
            itens = pedidoDTO.getItens().stream()
                    .map(this::toItemPedidoEntity)
                    .collect(Collectors.toList());
        }


        PedidoModel pedido = new PedidoModel();
        pedido.setNumeroPedido(pedidoDTO.getNumeroPedido());
        pedido.setDataCadastro(pedidoDTO.getDataCadastro());
        pedido.setSituacao(pedidoDTO.getSituacao());
        pedido.setDescontoTotal(pedidoDTO.getDescontoTotal());
        pedido.setValorTotal(pedidoDTO.getValorTotal());
        pedido.setItens(itens);

        return pedido;
    }




    // Método auxiliar para converter ItensPedidoDTO para ItemPedido (entidade)
    private ItensPedidoModel toItemPedidoEntity(ItensPedidoDTO dto) {
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

