package cepein.mapeamento.app.usecases.endereco;

import cepein.mapeamento.acore.domain.models.Endereco;
import cepein.mapeamento.app.gateways.EnderecoGateway;

import java.util.List;

public class EncontrarListaEnderecoUseCase {
    private EnderecoGateway enderecoGateway;
    public EncontrarListaEnderecoUseCase(EnderecoGateway enderecoGateway){
        this.enderecoGateway = enderecoGateway;
    }
    public List<Endereco> encontrarListaEndereco(){
        return this.enderecoGateway.encontrarTodosOsEnderecos();
    }
}
