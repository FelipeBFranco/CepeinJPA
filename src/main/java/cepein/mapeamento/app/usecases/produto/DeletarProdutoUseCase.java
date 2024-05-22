package cepein.mapeamento.app.usecases.produto;

import cepein.mapeamento.app.gateways.ProdutoGateway;

public class DeletarProdutoUseCase {
    private ProdutoGateway produtoGateway;
    public DeletarProdutoUseCase(ProdutoGateway produtoGateway){
        this.produtoGateway = produtoGateway;
    }
    public void deletarProduto(Long id){
        this.produtoGateway.deletarProdutoPorId(id);
    }
}
