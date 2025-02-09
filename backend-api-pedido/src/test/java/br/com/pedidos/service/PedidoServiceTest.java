package br.com.pedidos.service;

import br.com.pedidos.infra.PedidoConverter;
import br.com.pedidos.model.PedidoModel;
import br.com.pedidos.repository.PedidoRepository;
import br.com.pedidos.repository.ItensPedidoRepository;
import br.com.pedidos.producer.PedidoProducer;
import br.com.pedidos.producer.FecharPedidoProducer;
import br.com.pedidos.dto.PedidoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PedidoServiceTest {

    @Autowired
    private PedidoService pedidoService;

    @MockBean
    private PedidoRepository pedidoRepository;

    @MockBean
    private ItensPedidoRepository itensPedidoRepository;

    @MockBean
    private PedidoProducer pedidoProducer;

    @MockBean
    private FecharPedidoProducer fecharPedidoProducer;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PedidoConverter pedidoConverter;

    private PedidoModel pedido;

    @BeforeEach
    public void setUp() {
        // Inicializa um pedido de exemplo
        pedido = new PedidoModel();
        pedido.setNumeroPedido(123L);
        pedido.setDataCadastro(LocalDate.now());
        pedido.setSituacao("PROCESSANDO");
        pedido.setValorTotal(BigDecimal.ZERO);
        pedido.setDescontoTotal(BigDecimal.ZERO);
    }

    @Test
    public void testConsultarPedido() {
        when(pedidoRepository.buscarPedidoComItens(any(Long.class))).thenReturn(pedido);
        PedidoModel pedidoConsultado = pedidoService.consultarPedido(123L);
        assertNotNull(pedidoConsultado);
        assertEquals(123L, pedidoConsultado.getNumeroPedido());
    }

}
