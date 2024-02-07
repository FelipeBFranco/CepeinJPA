package cepein.mapeamento.pedido.service;

import cepein.mapeamento.pedido.dto.PedidoDto;
import cepein.mapeamento.pedido.model.Pedido;
import cepein.mapeamento.pedido.repository.PedidoRepository;
import cepein.mapeamento.pessoa.dto.PessoaDtoParaRelacionamento;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private Pedido buscarPedido(Long idPedido){
        return this.pedidoRepository.findById(idPedido)
                .orElseThrow(()-> new ObjectNotFoundException("Pedido não encontrado"));
    }

    public ResponseEntity<List<PedidoDto>> listarPedidos(){
        List<Pedido> pedidoList = this.pedidoRepository.findAll();
        List<PedidoDto> pedidoDtoList = PedidoDto.converter(pedidoList);
        return ResponseEntity.ok(pedidoDtoList);
    }
    public ResponseEntity<PedidoDto> procurarPedido(Long idPedido){
        Pedido pedido = this.buscarPedido(idPedido);
        PedidoDto pedidoDto = new PedidoDto(pedido.getId(),
                pedido.getDescricao(),
                pedido.getUuid(),
                PessoaDtoParaRelacionamento.converter(pedido.getPessoaListComJoinTable()),
                PessoaDtoParaRelacionamento.converterPessoaPedido(pedido.getPessoaListComEmbeddable()));
        return ResponseEntity.ok(pedidoDto);
    }
    @Transactional
    public ResponseEntity<Void> deletarPedido(Long idPedido){
        this.pedidoRepository.deleteById(idPedido);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
