package br.com.order.service;

import br.com.order.infra.PedidoConverter;
import br.com.order.model.PedidoModel;
import br.com.order.repository.ItensPedidoRepository;
import br.com.order.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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



}
