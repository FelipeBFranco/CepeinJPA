package cepein.mapeamento.infra.persistence.jpa.gateways;

import cepein.mapeamento.acore.domain.models.Pedido;
import cepein.mapeamento.app.gateways.PedidoGateway;
import cepein.mapeamento.infra.persistence.jpa.repositories.PedidoRepository;
import cepein.mapeamento.infra.persistence.jpa.mapper.JpaPedidoMapper;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaPedidoGateway implements PedidoGateway {
    private final PedidoRepository pedidoRepository;

    public JpaPedidoGateway(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido encontrarPedidoPorId(Long id) {
        return JpaPedidoMapper.toDomain(this.pedidoRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Pedido não encontrado")));
    }

    @Override
    public List<Pedido> encontrarTodosOsPedidos() {
        return JpaPedidoMapper.toDomain(this.pedidoRepository.findAll());
    }
    @Transactional
    @Override
    public void salvarPedido(Pedido pedido) {
        this.pedidoRepository.save(JpaPedidoMapper.toEntity(pedido));
    }
    @Transactional
    @Override
    public void deletarPedidoPorId(Long id) {
        this.pedidoRepository.deleteById(id);
    }
}
