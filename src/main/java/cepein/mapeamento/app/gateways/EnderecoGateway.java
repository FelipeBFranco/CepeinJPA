package cepein.mapeamento.app.gateways;

import cepein.mapeamento.acore.domain.models.Endereco;

import java.util.List;

public interface EnderecoGateway {
    Endereco encontrarEnderecoPorId(Long id);
    List<Endereco> encontrarTodosOsEnderecos();
    void salvarEndereco(Endereco endereco);
    void deletarEnderecoPorId(Long id);
}
