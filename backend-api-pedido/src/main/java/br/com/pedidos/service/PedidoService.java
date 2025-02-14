package br.com.pedidos.service;


import br.com.pedidos.dto.ItemPedidoResponse;
import br.com.pedidos.dto.PedidoDto;
import br.com.pedidos.infra.PedidoConverter;
import br.com.pedidos.model.PedidoModel;
import br.com.pedidos.producer.FecharPedidoProducer;
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
    private FecharPedidoProducer fecharPedidoParaFila;
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
        try {
            PedidoDto auxPedidoDto = modelMapper.map(pedidoRequest, PedidoDto.class);
            pedidoProducer.enviarPedidoParaFila(auxPedidoDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return "Pedido aguardando confirmacao...";
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


    public String calcularFecharPedido(Long numeroPedido) {
        PedidoModel pedido = pedidoRepository.buscarPedidoComItens(numeroPedido);
        PedidoDto auxPedidoDto;
        try {
            auxPedidoDto = pedidoConverter.converterParaDTO(pedido);
            auxPedidoDto.setSituacao("FINALIZADO");
            fecharPedidoParaFila.fecharPedidoParaFila(auxPedidoDto);
        } catch (
                JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return "Pedido fechado com sucesso...";
    }


}
