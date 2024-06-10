package cepein.mapeamento.infra.persistence.jpa.gateways;

import cepein.mapeamento.acore.domain.models.pessoa.PessoaPedidoCommand;
import cepein.mapeamento.app.gateways.PessoaPedidoGateway;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaPedido.JpaPessoaPedidoCommandEntity;
import cepein.mapeamento.infra.persistence.jpa.mapper.JpaPessoaPedidoMapper;
import cepein.mapeamento.infra.persistence.jpa.repositories.pessoa.PessoaPedidoCommandRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaPessoaPedidoGateway implements PessoaPedidoGateway {
    private final PessoaPedidoCommandRepository pessoaPedidoCommandRepository ;
    @Autowired
    public JpaPessoaPedidoGateway(PessoaPedidoCommandRepository pessoaPedidoCommandRepository) {
        this.pessoaPedidoCommandRepository = pessoaPedidoCommandRepository;
    }

@Transactional
    @Override
    public void salvar(List<PessoaPedidoCommand> pessoaPedidoCommandList) {
        List<JpaPessoaPedidoCommandEntity> jpaPessoaProdutoCommandEntityList = pessoaPedidoCommandList
                .stream()
                .map(JpaPessoaPedidoMapper::toEntity)
                .toList();
        this.pessoaPedidoCommandRepository.saveAll(jpaPessoaProdutoCommandEntityList);
    }
}
