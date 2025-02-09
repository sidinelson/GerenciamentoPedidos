package br.com.pedidos.service;

import br.com.pedidos.dto.ItensPedidoDto;
import br.com.pedidos.model.ItensPedidoModel;
import br.com.pedidos.model.PedidoModel;
import br.com.pedidos.producer.ItemPedidoProducer;
import br.com.pedidos.repository.ItensPedidoRepository;
import br.com.pedidos.repository.PedidoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ItensPedidoServiceTest {

    @InjectMocks
    private ItensPedidoService itensPedidoService;

    @MockBean
    private PedidoRepository pedidoRepository;

    @MockBean
    private ItensPedidoRepository itensPedidoRepository;

    @MockBean
    private ItemPedidoProducer itensPedidoProducer;

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

    @Test
    public void testCalcularItensPedido() {
        // Testa se o cálculo de itens está funcionando corretamente
        itensPedido.setPreco(BigDecimal.valueOf(100));
        itensPedido.setDesconto(BigDecimal.valueOf(10));
        itensPedido.setQuantidade(2);

        itensPedidoService.calcularItens(itensPedido);

        // Verifica se o valor total do item foi calculado corretamente
        BigDecimal expectedValorTotal = BigDecimal.valueOf(180); // (100 - 10) * 2
        assertEquals(expectedValorTotal, itensPedido.getValorTotal());
    }

}
