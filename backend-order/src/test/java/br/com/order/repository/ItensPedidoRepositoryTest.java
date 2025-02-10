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


}
