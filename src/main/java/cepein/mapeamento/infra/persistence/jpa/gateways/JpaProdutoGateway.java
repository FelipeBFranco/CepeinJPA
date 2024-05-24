package cepein.mapeamento.infra.persistence.jpa.gateways;

import cepein.mapeamento.acore.domain.models.produto.ProdutoCommand;
import cepein.mapeamento.acore.domain.models.produto.ProdutoQuery;
import cepein.mapeamento.app.gateways.ProdutoGateway;
import cepein.mapeamento.infra.persistence.jpa.repositories.produto.ProdutoCommandRepository;
import cepein.mapeamento.infra.persistence.jpa.repositories.produto.ProdutoQueryRepository;
import cepein.mapeamento.infra.persistence.jpa.mapper.JpaProdutoMapper;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaProdutoGateway implements ProdutoGateway {
    private final ProdutoQueryRepository produtoQueryRepository;
    private final ProdutoCommandRepository produtoCommandRepository;

    public JpaProdutoGateway(ProdutoQueryRepository produtoQueryRepository, ProdutoCommandRepository produtoCommandRepository) {
        this.produtoQueryRepository = produtoQueryRepository;
        this.produtoCommandRepository = produtoCommandRepository;
    }

    @Transactional
    @Override
    public void salvar(ProdutoCommand produtoCommand) {
        this.produtoCommandRepository.save(JpaProdutoMapper.toEntity(produtoCommand));
    }
    @Transactional
    @Override
    public void deletar(Long id) {
        this.produtoCommandRepository.deleteById(id);
    }

    @Override
    public ProdutoQuery buscar(Long id) {
        return JpaProdutoMapper.toDomain(this.produtoQueryRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("ProdutoQuery não encontrado")));

    }
    @Override
    public List<ProdutoQuery> encontrarTodasOsProduto() {

        return this.produtoQueryRepository.findAll()
                .stream()
                .map(JpaProdutoMapper::toDomain)
                .collect(Collectors.toList());
    }
}
