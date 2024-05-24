package cepein.mapeamento.app.usecases.endereco;

import cepein.mapeamento.app.gateways.EnderecoGateway;
import cepein.mapeamento.infra.adapters.http.forms.EnderecoForms;
import cepein.mapeamento.utils.clean.application.usecase.UseCaseRequest;

public class CadastrarEnderecoUseCase implements UseCaseRequest<EnderecoForms> {

    private final EnderecoGateway enderecoGateway;
    public CadastrarEnderecoUseCase(EnderecoGateway enderecoGateway){
        this.enderecoGateway = enderecoGateway;
    }
    @Override
    public void execute(EnderecoForms enderecoForms ){
        this.enderecoGateway.salvar(enderecoForms.converter());
    }
}
