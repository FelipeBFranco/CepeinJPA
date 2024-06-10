package cepein.mapeamento.app.usecases.produto;

import cepein.mapeamento.acore.domain.models.produto.ProdutoQuery;
import cepein.mapeamento.app.gateways.ProdutoGateway;
import cepein.mapeamento.utils.clean.application.usecase.UseCaseResponse;

import java.util.List;

public class EncontrarListaDeProdutoUseCase implements UseCaseResponse<List<ProdutoQuery>> {
    private final ProdutoGateway produtoGateway;
    public EncontrarListaDeProdutoUseCase(ProdutoGateway produtoGateway){
        this.produtoGateway = produtoGateway;
    }
    @Override
    public List<ProdutoQuery> execute() {
       return produtoGateway.encontrarTodasOsProduto();
    }
}
