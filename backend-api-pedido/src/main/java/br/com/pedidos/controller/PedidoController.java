package br.com.pedidos.controller;



import br.com.pedidos.model.PedidoModel;
import br.com.pedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;

@RestController
@Slf4j
@RequestMapping("api/pedido")
@AllArgsConstructor
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(summary = "Realizar cadastro de PedidoModel", method = "POST")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro de PedidoModel realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar cadastro de PedidoModel"),
    })
    @PostMapping
    public String save(@RequestBody PedidoModel pedidoRequest){

        return pedidoService.save(pedidoRequest);
    }


    @Operation(summary = "Listar Pedidos", method = "GET")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listar de Pedidos com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar calculo dos dados"),
    })
    @GetMapping(path = "/listarPedido", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<PedidoModel>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.findAll());
    }


    @Operation(summary = "Listar Pedidos", method = "GET")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listar de Pedidos com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar calculo dos dados"),
    })
    @GetMapping(path = "/consultarPedido/{numeroPedido}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PedidoModel> consultarPedido(@PathVariable Long numeroPedido) {
        return ResponseEntity.ok(pedidoService.consultarPedido(numeroPedido));
    }

    @Operation(summary = "Fechar Pedidos", method = "GET")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos fechado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar calculo dos dados"),
    })
    @GetMapping(path = "/fercharPedido/{numeroPedido}")
    public String fecharPedidos(@PathVariable Long numeroPedido) {
        return pedidoService.calcularFecharPedido(numeroPedido);
    }

}
