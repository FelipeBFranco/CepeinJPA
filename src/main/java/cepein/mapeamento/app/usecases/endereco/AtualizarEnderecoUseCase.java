package cepein.mapeamento.app.usecases.endereco;

import cepein.mapeamento.acore.domain.models.endereco.EnderecoQuery;
import cepein.mapeamento.app.gateways.EnderecoGateway;
import cepein.mapeamento.infra.adapters.http.forms.EnderecoForms;
import cepein.mapeamento.utils.clean.application.useCase.UseCaseRequest;

public class AtualizarEnderecoUseCase implements UseCaseRequest<EnderecoForms> {

    private final EnderecoGateway enderecoGateway;

    public AtualizarEnderecoUseCase(EnderecoGateway enderecoGateway){
        this.enderecoGateway = enderecoGateway;
    }
    @Override
    public void execute(EnderecoForms enderecoForms){
        EnderecoQuery enderecoQuery = this.enderecoGateway.buscar(enderecoForms.getId());
        this.enderecoGateway.salvar(enderecoForms.converter(enderecoQuery));
    }
}
