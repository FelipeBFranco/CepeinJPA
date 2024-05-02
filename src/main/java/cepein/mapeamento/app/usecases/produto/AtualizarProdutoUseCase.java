package cepein.mapeamento.app.usecases.produto;

import cepein.mapeamento.acore.domain.models.Produto;
import cepein.mapeamento.app.gateways.ProdutoGateway;

public class AtualizarProdutoUseCase {

    private ProdutoGateway produtoGateway;
     public AtualizarProdutoUseCase(ProdutoGateway produtoGateway){
         this.produtoGateway = produtoGateway;
     }
     public void atualizarProduto(Produto produto){
         this.produtoGateway.salvarProduto(produto);
     }
}
