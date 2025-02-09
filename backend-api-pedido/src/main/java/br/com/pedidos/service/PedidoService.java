package br.com.pedidos.service;


import br.com.pedidos.dto.ItemPedidoResponse;
import br.com.pedidos.dto.PedidoDTO;
import br.com.pedidos.dto.PedidoResponse;
import br.com.pedidos.infra.PedidoConverter;
import br.com.pedidos.model.PedidoModel;
import br.com.pedidos.producer.PedidoProducer;
import br.com.pedidos.repository.ItensPedidoRepository;
import br.com.pedidos.repository.PedidoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ItensPedidoRepository itensPedidoRepository;
    @Autowired
    private PedidoProducer pedidoProducer;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PedidoConverter pedidoConverter;

    @Transactional
    public String save(PedidoModel pedidoRequest) {

        if (pedidoRepository.existsByNumeroPedido(pedidoRequest.getNumeroPedido())) {
            throw new RuntimeException("Número de pedido já existe.");
        }

        if (isEmpty(pedidoRequest.getDataCadastro())) {
            LocalDate hoje = LocalDate.now();
            pedidoRequest.setDataCadastro(LocalDate.of(hoje.getYear(), hoje.getMonthValue(), hoje.getDayOfMonth()));
        }

        if (isEmpty(pedidoRequest.getValorTotal())) {
            pedidoRequest.setValorTotal(BigDecimal.ZERO);
        }

        if (isEmpty(pedidoRequest.getDescontoTotal())) {
            pedidoRequest.setDescontoTotal(BigDecimal.ZERO);
        }
        pedidoRequest.setSituacao("PROCESSANDO");
        calcularValores(pedidoRequest);
        try {
            PedidoDTO auxPedidoDTO = modelMapper.map(pedidoRequest, PedidoDTO.class);
            pedidoProducer.enviarPedidoParaFila(auxPedidoDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return "Pedido aguardando confirmacao...";
    }


    private void calcularValores(PedidoModel pedido) {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal descontoTotal = BigDecimal.ZERO;
        BigDecimal desconto = BigDecimal.ZERO;
        BigDecimal totalCalculado = BigDecimal.ZERO;

        if (!isEmpty(pedido.getItens())) {
            for (var item : pedido.getItens()) {

                total = BigDecimal.ZERO;
                descontoTotal = BigDecimal.ZERO;
                desconto = BigDecimal.ZERO;

                desconto = item.getDesconto();
                BigDecimal subtotal = item.getPreco()
                        .multiply(new BigDecimal(item.getQuantidade()));

                total = total.add(subtotal).subtract(desconto);
                descontoTotal = descontoTotal.add(desconto);
                totalCalculado = totalCalculado.add(total);
                item.setValorTotal(total);
            }
        }
        pedido.setValorTotal(totalCalculado);
        pedido.setDescontoTotal(descontoTotal);
    }

    public List<PedidoModel> findAll() {
        return pedidoRepository.findAll();
    }

    public PedidoModel consultarPedido(Long numeroPedido) {
        PedidoModel pedido = pedidoRepository.buscarPedidoComItens(numeroPedido);

        if (pedido == null) {
            throw new RuntimeException("Pedido não encontrado com o número: " + numeroPedido);
        }

        return pedido;
    }


    public List<ItemPedidoResponse> findByItensPedidosPedidos(Long numeroPedido) {
        return
                itensPedidoRepository.findBylistaNumeroPedidosItens(numeroPedido)
                        .stream()
                        .map(ItemPedidoResponse::of)
                        .collect(Collectors.toList());
    }

    public PedidoDTO findByPedido(Long numeroPedido) {

        PedidoModel pedido = pedidoRepository.findByPedido(numeroPedido);

        if (pedido == null) {
            throw new RuntimeException("Pedido não encontrado com o número: " + numeroPedido);
        }

        return new PedidoDTO(
                pedido.getNumeroPedido(),
                pedido.getDataCadastro(),
                pedido.getSituacao(),
                pedido.getDescontoTotal(),
                pedido.getValorTotal(),
                null
        );

    }

    public String calcularFecharPedido(Long numeroPedido){
        PedidoModel pedido = pedidoRepository.findByPedido(numeroPedido);
        if (pedido == null) {
            throw new RuntimeException("Pedido não encontrado com o número: " + numeroPedido);
        }
        pedido.setSituacao("FINALIZADO");
        pedidoRepository.save(pedido);

        return "Pedido fechado com sucesso...";
    }


}
