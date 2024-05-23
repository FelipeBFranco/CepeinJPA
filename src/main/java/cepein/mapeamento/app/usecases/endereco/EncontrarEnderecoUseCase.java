package cepein.mapeamento.app.usecases.endereco;

import cepein.mapeamento.acore.domain.models.endereco.EnderecoQuery;
import cepein.mapeamento.app.gateways.EnderecoGateway;
import cepein.mapeamento.utils.clean.application.useCase.UseCase;
import cepein.mapeamento.utils.clean.application.useCase.UseCaseResponse;

public class EncontrarEnderecoUseCase implements UseCase<Long,EnderecoQuery> {
    private final EnderecoGateway enderecoGateway;
    public EncontrarEnderecoUseCase(EnderecoGateway enderecoGateway){
        this.enderecoGateway = enderecoGateway;
    }
    @Override
    public EnderecoQuery execute(Long id){
        return this.enderecoGateway.buscar(id);
    }

}
