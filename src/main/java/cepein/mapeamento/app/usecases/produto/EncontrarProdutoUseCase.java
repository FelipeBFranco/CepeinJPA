package cepein.mapeamento.app.usecases.produto;

import cepein.mapeamento.acore.domain.models.produto.ProdutoQuery;
import cepein.mapeamento.app.gateways.ProdutoGateway;
import cepein.mapeamento.utils.clean.application.useCase.UseCase;

public class EncontrarProdutoUseCase implements UseCase<Long,ProdutoQuery> {
    private final ProdutoGateway produtoGateway;
    public EncontrarProdutoUseCase(ProdutoGateway produtoGateway){
        this.produtoGateway = produtoGateway;
    }
    @Override
    public ProdutoQuery execute(Long id){
        return this.produtoGateway.buscar(id);
    }
}
