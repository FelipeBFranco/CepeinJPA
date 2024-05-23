package cepein.mapeamento.app.usecases.endereco;

import cepein.mapeamento.acore.domain.models.endereco.EnderecoQuery;
import cepein.mapeamento.app.gateways.EnderecoGateway;
import cepein.mapeamento.utils.clean.application.useCase.UseCaseResponse;

import java.util.List;

public class EncontrarListaEnderecoUseCase implements UseCaseResponse<List<EnderecoQuery>> {
    private final EnderecoGateway enderecoGateway;

    public EncontrarListaEnderecoUseCase(EnderecoGateway enderecoGateway){
        this.enderecoGateway = enderecoGateway;
    }
    @Override
    public List<EnderecoQuery> execute(){
        return this.enderecoGateway.encontrarTodosOsEnderecos();
    }
}
