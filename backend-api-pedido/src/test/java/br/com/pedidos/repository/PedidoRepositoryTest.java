package br.com.pedidos.repository;

import br.com.pedidos.model.PedidoModel;
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
    public void testFindByNumeroPedido() {
        Optional<PedidoModel> pedidoConsultado = pedidoRepository.findByNumeroPedido(123L);
        assertTrue(pedidoConsultado.isPresent());
        assertEquals(123L, pedidoConsultado.get().getNumeroPedido());
        assertEquals("PENDENTE", pedidoConsultado.get().getSituacao());
        assertEquals(new BigDecimal("10"), pedidoConsultado.get().getDescontoTotal());
        assertEquals(new BigDecimal("1"), pedidoConsultado.get().getValorTotal());
    }

    @Test
    public void testFindBynumeroIDPedido() {
        Long idPedido = pedidoRepository.findBynumeroIDPedido(123L);
        assertNotNull(idPedido);
    }

    @Test
    public void testBuscarPedidoComItens() {
        PedidoModel pedidoComItens = pedidoRepository.buscarPedidoComItens(123L);

        assertNotNull(pedidoComItens);
        assertEquals(123L, pedidoComItens.getNumeroPedido());
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
