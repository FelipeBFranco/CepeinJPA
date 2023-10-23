package cepein.mapeamento.produto.service;

import cepein.mapeamento.produto.model.Produto;
import cepein.mapeamento.produto.repository.ProdutoRepository;

public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscarPeloId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

}
