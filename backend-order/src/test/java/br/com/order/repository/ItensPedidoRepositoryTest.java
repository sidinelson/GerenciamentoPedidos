package br.com.order.repository;

import br.com.order.model.ItensPedidoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
        // Inicializando os dados para o teste
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
    public void testFindByListaNumeroPedidosItens() {
        // Act: Realiza a consulta na lista de pedidos
        List<ItensPedidoModel> itensPedidos = itensPedidoRepository.findBylistaNumeroPedidosItens(123L);

        // Assert: Verifica os resultados
        assertNotNull(itensPedidos);
        assertFalse(itensPedidos.isEmpty());
        assertEquals(123L, itensPedidos.get(0).getNumeroPedido());
    }

    @Test
    public void testFindByListaNumeroPedidosItensNaoExistente() {
        // Act: Tenta consultar um número de pedido que não existe
        List<ItensPedidoModel> itensPedidos = itensPedidoRepository.findBylistaNumeroPedidosItens(999L);

        // Assert: Verifica que a consulta retorna uma lista vazia
        assertTrue(itensPedidos.isEmpty());
    }
}
