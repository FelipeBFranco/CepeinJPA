package cepein.mapeamento.infra.adapters.http.controllers;

import cepein.mapeamento.app.gateways.PedidoGateway;
import cepein.mapeamento.app.usecases.pedido.*;
import cepein.mapeamento.infra.adapters.http.dtos.PedidoDto;
import cepein.mapeamento.infra.adapters.http.forms.PedidoForms;
import cepein.mapeamento.infra.adapters.http.viewmodels.PedidoViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoGateway pedidoGateway;

    public PedidoController(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }



    @GetMapping("/listar")
    public ResponseEntity<List<PedidoDto>> listarPedidos(){

        EncontrarListaDePedidoUseCase encontrarListaDePedidoUseCase = new EncontrarListaDePedidoUseCase(this.pedidoGateway);
        List<PedidoDto> pedidoDtoList = encontrarListaDePedidoUseCase.encontrarListaPedido()
                .stream()
                .map(PedidoViewModel::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(pedidoDtoList);
    }

    @GetMapping("/procurar-por-id/{id}")
    public ResponseEntity<PedidoDto> procurarPedido(@PathVariable Long id){

        EncontrarPedidoUseCase encontrarPedidoUseCase = new EncontrarPedidoUseCase(this.pedidoGateway);
        PedidoDto pedidoDto = PedidoViewModel.toDto(encontrarPedidoUseCase.encontrarPedidoPorId(id));

        return ResponseEntity.ok(pedidoDto);
    }

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<HttpStatus> cadastrarPedido(@RequestBody PedidoForms pedidoForm){

        CadastrarPedidoUseCase cadastrarPedidoUseCase = new CadastrarPedidoUseCase(this.pedidoGateway);

        cadastrarPedidoUseCase.criarPedido(pedidoForm.converter());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path = "/atualizar")
    public ResponseEntity<HttpStatus> atualizarPedidoExistente(@RequestBody PedidoForms pedidoForms){

        EncontrarPedidoUseCase encontrarPedidoUseCase = new EncontrarPedidoUseCase(this.pedidoGateway);
        AtualizarPedidoUseCase atualizarPedidoUseCase = new AtualizarPedidoUseCase(this.pedidoGateway);

        atualizarPedidoUseCase.atualizarPedido(pedidoForms
                .converter(encontrarPedidoUseCase.encontrarPedidoPorId(pedidoForms.getId())));

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<HttpStatus> deletarPedido(@PathVariable Long id){

        DeletarPedidoUseCase deletarPedidoUseCase = new DeletarPedidoUseCase(this.pedidoGateway);

        deletarPedidoUseCase.deletarPedido(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
