package br.com.pedidos.service;

import br.com.pedidos.dto.ItensPedidoDTO;
import br.com.pedidos.dto.PedidoDTO;
import br.com.pedidos.infra.ValidationException;
import br.com.pedidos.model.ItensPedidoModel;
import br.com.pedidos.model.PedidoModel;
import br.com.pedidos.producer.ItemPedidoProducer;
import br.com.pedidos.repository.ItensPedidoRepository;
import br.com.pedidos.repository.PedidoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class ItensPedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ItensPedidoRepository itensPedidoRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ItemPedidoProducer itensPedidoProducer;

    public String save(ItensPedidoModel itensPedidoRequest){

        if(!isEmpty(duplicidadeProduto(itensPedidoRequest.getCodigoProduto()))){
            throw new ValidationException("Código do Produto já cadastrado nesse Pedido!");
        }

        if(isEmpty(itensPedidoRequest.getDataCadastro())){
            LocalDate hoje = LocalDate.now();
            itensPedidoRequest.setDataCadastro(LocalDate.of(hoje.getYear(), hoje.getMonthValue(), hoje.getDayOfMonth()));
        }

         if(isEmpty(itensPedidoRequest.getQuantidade())){
            itensPedidoRequest.setQuantidade(1);
        }
        PedidoModel auxPedido = new PedidoModel();
        Long numeroOrder = pedidoRepository.findBynumeroPedido(itensPedidoRequest.getNumeroPedido());
        auxPedido.setNumeroOrder(numeroOrder);
        itensPedidoRequest.setPedido(auxPedido);
        calcularItens(itensPedidoRequest);
        //itensPedidoRepository.save(itensPedidoRequest);
        try {
            ItensPedidoDTO auxItemPedidoDTO = modelMapper.map(itensPedidoRequest, ItensPedidoDTO.class);

            itensPedidoProducer.enviarItemPedidoParaFila(auxItemPedidoDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return "Itens aguardando confirmação";
    }
    public  void calcularItens(ItensPedidoModel itensPedido) {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal itensTotal = itensPedido.getPreco()
                .subtract(itensPedido.getDesconto())
                .multiply(BigDecimal.valueOf(itensPedido.getQuantidade()));
        total = total.add(itensTotal);
        itensPedido.setValorTotal(total);
    }
    public ResponseEntity<ItensPedidoModel> findAll() {
        return (ResponseEntity<ItensPedidoModel>) itensPedidoRepository.findAll();
    }

    public List<ItensPedidoModel> findByItensPedidosPedidos(Long numeroPedido) {
        var pedido = new PedidoModel();
        pedido.setNumeroPedido(numeroPedido);
        return itensPedidoRepository
                .findByItensPedidosPedidos(numeroPedido);
    }

    public Optional<ItensPedidoModel> duplicidadeProduto(String itensPedidoRequest) {
        return Optional.empty();
    }

}
