package br.com.order.service;



import br.com.order.dto.ItemPedidoResponse;
import br.com.order.model.ItensPedidoModel;
import br.com.order.model.PedidoModel;
import br.com.order.model.ProdutoExternoBModel;
import br.com.order.repository.ItensPedidoRepository;
import br.com.order.repository.PedidoRepository;
import br.com.order.repository.ProdutoExternoBRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoExternoBRepository produtoExternoBRepository;
    @Autowired
    private ItensPedidoRepository itensPedidoRepository;

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
        pedidoRepository.save(pedidoRequest);

        if(!isEmpty(pedidoRequest.getItens()) && pedidoRequest.getItens().size() >0){
            itensPedidoRepository.atualizarPedidoEmItens(pedidoRequest.getNumeroPedido(), pedidoRequest);
        }

        return "Pedido registrado com sucesso...";
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


    public PedidoModel atualizarTotalPorItemPedido(Long numeroPedido) {
        Optional<PedidoModel> pedidoExistenteOpt = pedidoRepository.findBynumeroOrder(numeroPedido);
        if (!pedidoExistenteOpt.isPresent()) {
            throw new RuntimeException("Pedido não encontrado com o número: " + numeroPedido);
        }
        PedidoModel pedidoExistente = pedidoExistenteOpt.get();
        this.calcularValores(pedidoExistente);
        return pedidoRepository.save(pedidoExistente);
    }



    public String fecharPedido(PedidoModel pedidoModel){
        pedidoRepository.save(pedidoModel);


        List<ItensPedidoModel> itens = itensPedidoRepository.findByItensPedidos(pedidoModel.getNumeroPedido());
        if (!isEmpty(itens)) {
            for (var item : itens) {
                ProdutoExternoBModel produto = new ProdutoExternoBModel();
                produto.setNumeroPedido(item.getNumeroPedido());
                produto.setDataCadastro(item.getDataCadastro());
                produto.setProduto(item.getCodigoProduto());
                produto.setDescricaoProduto(item.getDescricaoProduto());
                produto.setQuantidade(item.getQuantidade());
                produto.setDesconto(item.getDesconto());
                produto.setPreco(item.getPreco());
                produto.setValorTotal(item.getValorTotal());
                produtoExternoBRepository.save(produto);
            }
        }

        return "Pedido fechado com sucesso...";
    }

}
