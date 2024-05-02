package cepein.mapeamento.app.usecases.produto;

import cepein.mapeamento.acore.domain.models.Produto;
import cepein.mapeamento.app.gateways.ProdutoGateway;

public class CadastrarProdutoUseCase {
    private ProdutoGateway produtoGateway;
    public CadastrarProdutoUseCase(ProdutoGateway produtoGateway){
        this.produtoGateway = produtoGateway;
    }
    public void criarProduto(Produto produto){
        this.produtoGateway.salvarProduto(produto);
    }
}
