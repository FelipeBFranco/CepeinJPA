package cepein.mapeamento.app.usecases.produto;

import cepein.mapeamento.acore.domain.models.produto.ProdutoQuery;
import cepein.mapeamento.app.gateways.ProdutoGateway;
import cepein.mapeamento.infra.adapters.http.forms.ProdutoForms;
import cepein.mapeamento.utils.clean.application.useCase.UseCaseRequest;

public class AtualizarProdutoUseCase implements UseCaseRequest<ProdutoForms> {

    private final ProdutoGateway produtoGateway;
     public AtualizarProdutoUseCase(ProdutoGateway produtoGateway){
         this.produtoGateway = produtoGateway;
     }
     @Override
     public void execute(ProdutoForms produtoForms ){
         ProdutoQuery produtoQuery = this.produtoGateway.buscar(produtoForms.getId());
         this.produtoGateway.salvar(produtoForms.converter(produtoQuery));
     }
}
