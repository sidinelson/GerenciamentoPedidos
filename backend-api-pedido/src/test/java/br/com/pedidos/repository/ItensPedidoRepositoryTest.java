package br.com.pedidos.repository;

import br.com.pedidos.model.ItensPedidoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ItensPedidoRepositoryTest {

    @Autowired
    private ItensPedidoRepository itensPedidoRepository;

    private ItensPedidoModel itensPedidoModel;

    @BeforeEach
    public void setUp() {
        itensPedidoModel = new ItensPedidoModel();
        itensPedidoModel.setNumeroPedido(123L);
        itensPedidoModel.setCodigoProduto("123");
        itensPedidoModel.setDescricaoProduto("teste Produto");
        itensPedidoModel.setQuantidade(1);
        itensPedidoModel.setDesconto(BigDecimal.TEN);
        itensPedidoModel.setValorTotal(BigDecimal.TEN);
        itensPedidoRepository.save(itensPedidoModel); // Salva o item no banco para os testes
    }

    @Test
    public void testFindByItensPedidosPedidos() {
        List<ItensPedidoModel> itensPedidos = itensPedidoRepository.findByItensPedidosPedidos(123L);

        assertNotNull(itensPedidos);
        assertFalse(itensPedidos.isEmpty());
        assertEquals(123L, itensPedidos.get(0).getNumeroPedido());
    }

    @Test
    public void testFindByListaNumeroPedidosItens() {
        List<ItensPedidoModel> itensPedidos = itensPedidoRepository.findBylistaNumeroPedidosItens(123L);

        assertNotNull(itensPedidos);
        assertFalse(itensPedidos.isEmpty());
        assertEquals(123L, itensPedidos.get(0).getNumeroPedido());
    }

    @Test
    public void testFindByNumeroPedidoNaoExistente() {
        List<ItensPedidoModel> itensPedidos = itensPedidoRepository.findByItensPedidosPedidos(999L);

        assertTrue(itensPedidos.isEmpty());
    }

    @Test
    public void testFindByListaNumeroPedidosItensNaoExistente() {
        List<ItensPedidoModel> itensPedidos = itensPedidoRepository.findBylistaNumeroPedidosItens(999L);
        assertTrue(itensPedidos.isEmpty());
    }
}
