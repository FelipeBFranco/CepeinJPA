package cepein.mapeamento.app.usecases.produto;

import cepein.mapeamento.acore.domain.models.Produto;
import cepein.mapeamento.app.gateways.ProdutoGateway;

import java.util.List;

public class EncontrarListaDeProdutoUseCase {
    private ProdutoGateway produtoGateway;
    public EncontrarListaDeProdutoUseCase(ProdutoGateway produtoGateway){
        this.produtoGateway = produtoGateway;
    }

    public List<Produto> encontrarListaProduto() {
       return produtoGateway.encontrarTodasOsProduto();
    }
}
