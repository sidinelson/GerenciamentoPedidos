package br.com.order.repository;

import br.com.order.model.PedidoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    private PedidoModel pedido;

    @BeforeEach
    public void setUp() {
        pedido = new PedidoModel();
        pedido.setNumeroPedido(123L);
        pedido.setSituacao("PENDENTE");
        pedido.setDescontoTotal(BigDecimal.TEN);
        pedido.setValorTotal(BigDecimal.ONE);
        pedido.setDataCadastro(LocalDate.now());
        pedidoRepository.save(pedido);
    }
    @Test
    public void testExistsByNumeroPedido() {
        boolean existe = pedidoRepository.existsByNumeroPedido(123L);
    }

    @Test
    public void testPedidoNaoExistente() {
        boolean existe = pedidoRepository.existsByNumeroPedido(999L);

        assertFalse(existe);
    }
}
