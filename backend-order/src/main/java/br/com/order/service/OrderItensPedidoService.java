package br.com.order.service;

import br.com.order.infra.ValidationException;
import br.com.order.model.ItensPedidoModel;
import br.com.order.model.PedidoModel;
import br.com.order.repository.ItensPedidoRepository;
import br.com.order.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class OrderItensPedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ItensPedidoRepository itensPedidoRepository;
    @Autowired
    private OrderPedidoService orderPedidoService;

    public ItensPedidoModel save(ItensPedidoModel itensPedidoRequest) {

        if (!isEmpty(duplicidadeProduto(itensPedidoRequest.getCodigoProduto()))) {
            throw new ValidationException("Código do Produto já cadastrado nesse Pedido!");
        }

        if (isEmpty(itensPedidoRequest.getDataCadastro())) {
            LocalDate hoje = LocalDate.now();
            itensPedidoRequest.setDataCadastro(LocalDate.of(hoje.getYear(), hoje.getMonthValue(), hoje.getDayOfMonth()));
        }

        if (isEmpty(itensPedidoRequest.getQuantidade())) {
            itensPedidoRequest.setQuantidade(1);
        }

        PedidoModel auxPedido = new PedidoModel();
        Long numeroOrder = pedidoRepository.findBynumeroPedido(itensPedidoRequest.getNumeroPedido());
        auxPedido.setNumeroOrder(numeroOrder);
        itensPedidoRequest.setPedido(auxPedido);
        calcularItens(itensPedidoRequest);

        var itens = itensPedidoRepository.save(itensPedidoRequest);
        orderPedidoService.atualizarTotalPorItemPedido(itensPedidoRequest.getNumeroPedido());
        return itens;
    }

    public void calcularItens(ItensPedidoModel itensPedido) {
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
                .findByItensPedidos(numeroPedido);
    }

    public Optional<ItensPedidoModel> duplicidadeProduto(String itensPedidoRequest) {
        return Optional.empty();
    }

}
