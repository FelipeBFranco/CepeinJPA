package cepein.mapeamento.app.gateways;

import cepein.mapeamento.acore.domain.models.Produto;

import java.util.List;

public interface ProdutoGateway {
    Produto encontrarProdutoPorId(Long id);
    List<Produto> encontrarTodasOsProduto();
    void salvarProduto(Produto produto);
    void deletarProdutoPorId(Long id);
}
