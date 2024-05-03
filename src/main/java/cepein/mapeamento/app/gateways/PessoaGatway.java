package cepein.mapeamento.app.gateways;

import cepein.mapeamento.acore.domain.models.Pessoa;

import java.util.List;

public interface PessoaGatway {

    Pessoa encontrarPessoaPorId(Long id);
    List<Pessoa> encontrarTodasAsPessoa();
    void salvarPessoa(Pessoa pessoa);
    void deletarPessoaPorId(Long id);
}
