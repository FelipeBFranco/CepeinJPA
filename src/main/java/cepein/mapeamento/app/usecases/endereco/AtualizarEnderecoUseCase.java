package cepein.mapeamento.app.usecases.endereco;

import cepein.mapeamento.acore.domain.models.Endereco;
import cepein.mapeamento.app.gateways.EnderecoGateway;

public class AtualizarEnderecoUseCase {

    private EnderecoGateway enderecoGateway;

    public AtualizarEnderecoUseCase(EnderecoGateway enderecoGateway){
        this.enderecoGateway = enderecoGateway;
    }
    public void atualizarEndereco(Endereco endereco){
        this.enderecoGateway.salvarEndereco(endereco);
    }
}
