package cepein.mapeamento.infra.persistence.jpa.gateways;

import cepein.mapeamento.acore.domain.models.pessoa.PessoaProdutoCommand;
import cepein.mapeamento.app.gateways.PessoaProdutoGateway;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaProduto.JpaPessoaProdutoCommandEntity;
import cepein.mapeamento.infra.persistence.jpa.mapper.JpaPessoProdutoMapper;
import cepein.mapeamento.infra.persistence.jpa.repositories.pessoa.PessoaProdutoCommandRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaPessoaProdutoGateway implements PessoaProdutoGateway {
    private final PessoaProdutoCommandRepository pessoaProdutoCommandRepository;

    @Autowired
    public JpaPessoaProdutoGateway(PessoaProdutoCommandRepository pessoaProdutoCommandRepository) {
        this.pessoaProdutoCommandRepository = pessoaProdutoCommandRepository;
    }
    @Transactional
    @Override
    public void salvar(List<PessoaProdutoCommand> pessoaProdutoCommandList) {
        List<JpaPessoaProdutoCommandEntity> jpaPessoaProdutoCommandEntityList = pessoaProdutoCommandList
                .stream()
                .map(JpaPessoProdutoMapper::toEntity)
                .toList();
        this.pessoaProdutoCommandRepository.saveAll(jpaPessoaProdutoCommandEntityList);
    }
}
