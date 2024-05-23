package cepein.mapeamento.app.usecases.produto;

import cepein.mapeamento.app.gateways.ProdutoGateway;
import cepein.mapeamento.infra.adapters.http.forms.ProdutoForms;
import cepein.mapeamento.utils.clean.application.useCase.UseCaseRequest;

public class CadastrarProdutoUseCase implements UseCaseRequest<ProdutoForms> {
    private final ProdutoGateway produtoGateway;
    public CadastrarProdutoUseCase(ProdutoGateway produtoGateway){
        this.produtoGateway = produtoGateway;
    }
    @Override
    public void execute(ProdutoForms produtoForms){
        this.produtoGateway.salvar(produtoForms.converter());
    }
}
