package cepein.mapeamento.app.usecases.produto;

import cepein.mapeamento.acore.domain.models.Produto;
import cepein.mapeamento.app.gateways.ProdutoGateway;

public class EncontrarProdutoUseCase {
    private ProdutoGateway produtoGateway;
    public EncontrarProdutoUseCase(ProdutoGateway produtoGateway){
        this.produtoGateway = produtoGateway;
    }

    public Produto encontrarProdutoPorId(Long id){
        return this.produtoGateway.encontrarProdutoPorId(id);
    }
}
