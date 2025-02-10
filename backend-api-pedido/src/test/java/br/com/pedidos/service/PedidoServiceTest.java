package br.com.pedidos.service;

import br.com.pedidos.dto.PedidoDto;
import br.com.pedidos.model.PedidoModel;
import br.com.pedidos.repository.PedidoRepository;
import br.com.pedidos.producer.PedidoProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PedidoServiceTest {
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PedidoProducer pedidoProducer;

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
    }

    @Test
    void testSavePedido() throws JsonProcessingException {
        when(pedidoRepository.existsByNumeroPedido(anyLong())).thenReturn(false);
        when(pedidoRepository.save(any(PedidoModel.class))).thenReturn(pedido);
        String result = pedidoService.save(pedido);
        assertEquals("Pedido aguardando confirmacao...", result);

    }

    @Test
    void testSavePedidoComNumeroExistente() {
        when(pedidoRepository.existsByNumeroPedido(anyLong())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pedidoService.save(pedido);
        });

        assertEquals("Número de pedido já existe.", exception.getMessage());
    }

    @Test
    void testConsultarPedido() {
        when(pedidoRepository.buscarPedidoComItens(anyLong())).thenReturn(pedido);

        PedidoModel result = pedidoService.consultarPedido(pedido.getNumeroPedido());

        assertNotNull(result);
        assertEquals(pedido.getNumeroPedido(), result.getNumeroPedido());
    }

    @Test
    void testConsultarPedidoNaoEncontrado() {
        when(pedidoRepository.buscarPedidoComItens(anyLong())).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pedidoService.consultarPedido(999L);
        });

        assertEquals("Pedido não encontrado com o número: 999", exception.getMessage());
    }

}
