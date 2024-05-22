package cepein.mapeamento.app.usecases.endereco;

import cepein.mapeamento.acore.domain.models.Endereco;
import cepein.mapeamento.app.gateways.EnderecoGateway;

public class CadastrarEnderecoUseCase {

    private EnderecoGateway enderecoGateway;
    public CadastrarEnderecoUseCase(EnderecoGateway enderecoGateway){
        this.enderecoGateway = enderecoGateway;
    }

    public void criarEndereco(Endereco endereco){
        this.enderecoGateway.salvarEndereco(endereco);
    }
}
