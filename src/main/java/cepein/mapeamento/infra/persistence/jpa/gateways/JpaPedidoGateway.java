package cepein.mapeamento.infra.persistence.jpa.gateways;

import cepein.mapeamento.acore.domain.models.pedido.PedidoCommand;
import cepein.mapeamento.acore.domain.models.pedido.PedidoQuery;
import cepein.mapeamento.app.gateways.PedidoGateway;
import cepein.mapeamento.infra.persistence.jpa.repositories.pedido.PedidoCommandRepository;
import cepein.mapeamento.infra.persistence.jpa.repositories.pedido.PedidoQueryRepository;
import cepein.mapeamento.infra.persistence.jpa.mapper.JpaPedidoMapper;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaPedidoGateway implements PedidoGateway {
    private final PedidoQueryRepository pedidoQueryRepository;
    private final PedidoCommandRepository pedidoCommandRepository;
    public JpaPedidoGateway(PedidoQueryRepository pedidoQueryRepository
            ,PedidoCommandRepository pedidoCommandRepository) {
        this.pedidoQueryRepository = pedidoQueryRepository;
        this.pedidoCommandRepository = pedidoCommandRepository;
    }

    @Transactional
    @Override
    public void salvar(PedidoCommand pedidoCommand) {

        this.pedidoCommandRepository.save(JpaPedidoMapper.toEntity(pedidoCommand));
    }
    @Transactional
    @Override
    public void deletar(Long id) {
        this.pedidoCommandRepository.deleteById(id);
    }
    @Override
    public PedidoQuery buscar(Long id) {
        return JpaPedidoMapper.toDomain(this.pedidoQueryRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Pedido não encontrado")));
    }
    @Override
    public PedidoQuery buscar(String uuid) {
        return  JpaPedidoMapper.toDomain(this.pedidoQueryRepository.findByUuid( uuid)
                .orElseThrow(()-> new ObjectNotFoundException("Pedido não encontrado")));
    }
    @Override
    public List<PedidoQuery> encontrarTodosOsPedidos() {
        return JpaPedidoMapper.toDomain(this.pedidoQueryRepository.findAll());
    }
}
