package cepein.mapeamento.app.usecases.endereco;

import cepein.mapeamento.app.gateways.EnderecoGateway;

public class DeletarEnderecoUseCase {
    private EnderecoGateway enderecoGateway;
    public DeletarEnderecoUseCase(EnderecoGateway enderecoGateway){
        this.enderecoGateway = enderecoGateway;
    }
    public void deletarEndereco(Long id){
        System.out.println("useCase");
        this.enderecoGateway.deletarEnderecoPorId(id);
    }
}
