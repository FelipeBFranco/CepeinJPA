package cepein.mapeamento.infra.persistence.jpa.gateways;

import cepein.mapeamento.app.gateways.ProdutoGateway;
import cepein.mapeamento.acore.domain.models.Produto;
import cepein.mapeamento.infra.persistence.jpa.repositories.ProdutoRepository;
import cepein.mapeamento.infra.persistence.jpa.mapper.JpaProdutoMapper;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaProdutoGateway implements ProdutoGateway {
    private final ProdutoRepository produtoRepository;

    public JpaProdutoGateway(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto encontrarProdutoPorId(Long id) {
        return JpaProdutoMapper.toDomain(this.produtoRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Produto não encontrado")));
    }

    @Override
    public List<Produto> encontrarTodasOsProduto() {

        return this.produtoRepository.findAll()
                .stream()
                .map(JpaProdutoMapper::toDomain)
                .collect(Collectors.toList());
    }
    @Transactional
    @Override
    public void salvarProduto(Produto produto) {
        this.produtoRepository.save(JpaProdutoMapper.toEntity(produto));
    }
    @Transactional
    @Override
    public void deletarProdutoPorId(Long id) {
        this.produtoRepository.deleteById(id);
    }
}
