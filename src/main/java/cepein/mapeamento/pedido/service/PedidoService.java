package cepein.mapeamento.pedido.service;

import cepein.mapeamento.pedido.dto.PedidoDto;
import cepein.mapeamento.pedido.model.Pedido;
import cepein.mapeamento.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    public ResponseEntity<List<PedidoDto>> listarPedidos(){
        List<Pedido> pedidoList = this.pedidoRepository.findAll();
        List<PedidoDto> pedidoDtoList = PedidoDto.converter(pedidoList);

        return ResponseEntity.ok(pedidoDtoList);
    }
}
