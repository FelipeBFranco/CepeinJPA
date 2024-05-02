package cepein.mapeamento.app.usecases.endereco;

import cepein.mapeamento.acore.domain.models.Endereco;
import cepein.mapeamento.app.gateways.EnderecoGateway;

public class EncontrarEnderecoUseCase {
    private EnderecoGateway enderecoGateway;
    public EncontrarEnderecoUseCase(EnderecoGateway enderecoGateway){
        this.enderecoGateway = enderecoGateway;
    }
    public Endereco encontrarEndereco(Long id){
        return this.enderecoGateway.encontrarEnderecoPorId(id);
    }
}
