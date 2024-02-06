package cepein.mapeamento.pedido.resource;

import cepein.mapeamento.pedido.dto.PedidoDto;
import cepein.mapeamento.pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
