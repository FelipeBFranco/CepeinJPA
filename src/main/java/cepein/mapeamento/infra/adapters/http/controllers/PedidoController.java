package cepein.mapeamento.infra.adapters.http.controllers;

import cepein.mapeamento.app.gateways.PedidoGateway;
import cepein.mapeamento.app.usecases.pedido.*;
import cepein.mapeamento.infra.adapters.http.dtos.PedidoDto;
import cepein.mapeamento.infra.adapters.http.forms.PedidoForms;
import cepein.mapeamento.infra.adapters.http.viewmodels.PedidoViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoGateway pedidoGateway;
    @Autowired
    public PedidoController(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<PedidoDto> procurarPedido(@PathVariable Long id){

        EncontrarPedidoUseCase encontrarPedidoUseCase = new EncontrarPedidoUseCase(this.pedidoGateway);
        PedidoDto pedidoDto = PedidoViewModel.toDto(encontrarPedidoUseCase.execute(id));

        return ResponseEntity.ok(pedidoDto);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<PedidoDto>> listarPedidos(){

        EncontrarListaDePedidoUseCase encontrarListaDePedidoUseCase = new EncontrarListaDePedidoUseCase(this.pedidoGateway);
        List<PedidoDto> pedidoDtoList = encontrarListaDePedidoUseCase.execute()
                .stream()
                .map(PedidoViewModel::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(pedidoDtoList);
    }
    @PostMapping(path = "/cadastrar")
    public ResponseEntity<HttpStatus> cadastrarPedido(@RequestBody PedidoForms pedidoForm){

        CadastrarPedidoUseCase cadastrarPedidoUseCase = new CadastrarPedidoUseCase(this.pedidoGateway);

        cadastrarPedidoUseCase.execute(pedidoForm);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path = "/atualizar")
    public ResponseEntity<HttpStatus> atualizarPedidoExistente(@RequestBody PedidoForms pedidoForms){

        AtualizarPedidoUseCase atualizarPedidoUseCase = new AtualizarPedidoUseCase(this.pedidoGateway);

        atualizarPedidoUseCase.execute(pedidoForms);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<HttpStatus> deletarPedido(@PathVariable Long id){

        DeletarPedidoUseCase deletarPedidoUseCase = new DeletarPedidoUseCase(this.pedidoGateway);

        deletarPedidoUseCase.execute(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
