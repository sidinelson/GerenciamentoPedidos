package br.com.pedidos.controller;


import br.com.pedidos.model.ItensPedidoModel;
import br.com.pedidos.model.PedidoModel;
import br.com.pedidos.service.ItensPedidoService;
import br.com.pedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/itenspedido")
@AllArgsConstructor
public class ItensPedidoController {



    @Autowired
    private ItensPedidoService itensPedidoService;


    @Operation(summary = "Realizar cadastro de Itens PedidoModel", method = "POST")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro de Itens PedidoModel realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar cadastro de Itens PedidoModel"),
    })
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
                 consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String save(@RequestBody ItensPedidoModel itensPedidoRequest){

        itensPedidoService.save(itensPedidoRequest);
        return "Item Pedido aguardando confirmação";
    }


    @Operation(summary = "Listar todos os itens Pedidos", method = "GET")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ItensPedidoModel> findAll() {
        return itensPedidoService.findAll();
    }


}
