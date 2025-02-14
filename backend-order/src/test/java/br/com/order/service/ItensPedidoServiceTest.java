package br.com.order.service;

import br.com.order.model.ItensPedidoModel;
import br.com.order.model.PedidoModel;
import br.com.order.repository.ItensPedidoRepository;
import br.com.order.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class ItensPedidoServiceTest {

    @InjectMocks
    private ItensPedidoService itensPedidoService;

    @MockBean
    private PedidoRepository pedidoRepository;

    @MockBean
    private ItensPedidoRepository itensPedidoRepository;



    @MockBean
    private ModelMapper modelMapper;

    private ItensPedidoModel itensPedido;
    private PedidoModel auxPedido;
    @BeforeEach
    public void setUp() {
        // Inicializa um item de pedido de exemplo
        itensPedido = new ItensPedidoModel();
        auxPedido = new PedidoModel();
        auxPedido.setNumeroOrder(1L);
        auxPedido.setNumeroPedido(123L);
        auxPedido.setDataCadastro(LocalDate.now());
        auxPedido.setDescontoTotal(BigDecimal.ZERO);
        auxPedido.setValorTotal(BigDecimal.ONE);
        itensPedido.setPedido(auxPedido);
        itensPedido.setNumeroPedido(123L);
        itensPedido.setCodigoProduto("P001");
        itensPedido.setDescricaoProduto("Produto Exemplo");
        itensPedido.setPreco(BigDecimal.valueOf(100));
        itensPedido.setDesconto(BigDecimal.valueOf(10));
        itensPedido.setQuantidade(2);
        itensPedido.setDataCadastro(LocalDate.now());
    }



}
