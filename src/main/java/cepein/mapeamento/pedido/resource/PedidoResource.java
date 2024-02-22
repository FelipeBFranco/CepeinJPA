package cepein.mapeamento.pedido.resource;

import cepein.mapeamento.pedido.dto.PedidoDto;
import cepein.mapeamento.pedido.forms.PedidoForms;
import cepein.mapeamento.pedido.service.PedidoService;
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
        return this.pedidoService.listarPedidos();
    }

    @GetMapping("/procurar-por-id/{idPedido}")
    public ResponseEntity<PedidoDto> procurarPedido(@PathVariable Long idPedido){
        return this.pedidoService.procurarPedido(idPedido);
    }

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<HttpStatus> cadastrarPedido(@RequestBody PedidoForms pedidoForm){
        return this.pedidoService.cadastrarPedido(pedidoForm);
    }

    @PutMapping(path = "/atualizar/{idPedido}")
    public ResponseEntity<HttpStatus> atualizarPedidoExistente(@RequestBody PedidoForms pedidoForms, @PathVariable Long idPedido){
        return this.pedidoService.atualizarPedidoExistente(pedidoForms, idPedido);
    }

    @DeleteMapping("/deletar/{idPedido}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long idPedido){
        return this.pedidoService.deletarPedido(idPedido);
    }
}
