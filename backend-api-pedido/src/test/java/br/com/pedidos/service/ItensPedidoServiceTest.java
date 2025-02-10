package br.com.pedidos.service;

import br.com.pedidos.dto.ItensPedidoDto;
import br.com.pedidos.model.ItensPedidoModel;
import br.com.pedidos.model.PedidoModel;
import br.com.pedidos.producer.ItemPedidoProducer;
import br.com.pedidos.repository.ItensPedidoRepository;
import br.com.pedidos.repository.PedidoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.ValidationException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
public class ItensPedidoServiceTest {

    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private ItensPedidoService itensPedidoService;

    @Mock
    private ItensPedidoRepository itensPedidoRepository;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ItemPedidoProducer itensPedidoProducer;

    private ItensPedidoModel itensPedido;
    private PedidoModel pedido;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pedido = new PedidoModel();
        pedido.setNumeroPedido(123L);
        pedido.setDataCadastro(LocalDate.now());
        pedido.setValorTotal(BigDecimal.valueOf(100.0));
        pedido.setDescontoTotal(BigDecimal.valueOf(10.0));
        pedido.setSituacao("PROCESSANDO");

        itensPedido = new ItensPedidoModel();
        itensPedido.setNumeroPedido(123L);
        itensPedido.setCodigoProduto("ABC123");
        itensPedido.setDescricaoProduto("Produto de Teste");
        itensPedido.setQuantidade(2);
        itensPedido.setPreco(BigDecimal.valueOf(50.0));
        itensPedido.setDesconto(BigDecimal.valueOf(5.0));
        itensPedido.setDataCadastro(LocalDate.now());
        itensPedido.setPedido(pedido);
    }

    @Test
    void testSaveItensPedido() throws JsonProcessingException {
        when(pedidoRepository.findBynumeroIDPedido(anyLong())).thenReturn(123L);

        String result = itensPedidoService.save(itensPedido);

        assertEquals("Itens aguardando confirmação", result);
    }


    @Test
    void testCalcularItens() {
        itensPedidoService.calcularItens(itensPedido);

        assertNotNull(itensPedido.getValorTotal());
        assertEquals(BigDecimal.valueOf(90.0), itensPedido.getValorTotal()); // 2 * (50 - 5)
    }

    @Test
    void testFindAllItensPedido() {
        List<ItensPedidoModel> itensPedidoList = new ArrayList<>();
        when(itensPedidoRepository.findAll()).thenReturn(itensPedidoList);
        assertNotNull(itensPedidoList);
    }

    @Test
    void testFindByItensPedidosPedidos() {

        when(itensPedidoRepository.findByItensPedidosPedidos(anyLong())).thenReturn(List.of(itensPedido));

        var result = itensPedidoService.findByItensPedidosPedidos(pedido.getNumeroPedido());

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
