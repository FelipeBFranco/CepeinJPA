package cepein.mapeamento.infra.resource;

import cepein.mapeamento.infra.dto.PedidoDto;
import cepein.mapeamento.infra.forms.PedidoForms;
import cepein.mapeamento.infra.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoResource {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoResource(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PedidoDto>> listarPedidos(){
        return ResponseEntity.ok(this.pedidoService.listarPedidos());
    }

    @GetMapping("/procurar-por-id/{idPedido}")
    public ResponseEntity<PedidoDto> procurarPedido(@PathVariable Long idPedido){
        return ResponseEntity.ok(this.pedidoService.procurarPedido(idPedido));
    }

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<HttpStatus> cadastrarPedido(@RequestBody PedidoForms pedidoForm){
        this.pedidoService.cadastrarPedido(pedidoForm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path = "/atualizar/{idPedido}")
    public ResponseEntity<HttpStatus> atualizarPedidoExistente(@RequestBody PedidoForms pedidoForms, @PathVariable Long idPedido){
        this.pedidoService.atualizarPedidoExistente(pedidoForms, idPedido);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/deletar/{idPedido}")
    public ResponseEntity<HttpStatus> deletarPedido(@PathVariable Long idPedido){
        this.pedidoService.deletarPedido(idPedido);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
