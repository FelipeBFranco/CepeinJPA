package cepein.mapeamento.app.usecases.pessoa;

import cepein.mapeamento.acore.domain.models.Pessoa;
import cepein.mapeamento.app.gateways.PessoaGatway;

public class CadastrarPessoaUseCase {
    private PessoaGatway pessoaGatway;

    public CadastrarPessoaUseCase(PessoaGatway pessoaGatway){
        this.pessoaGatway = pessoaGatway;
    }
    public void criarPessoa(Pessoa pessoa){
        this.pessoaGatway.salvarPessoa(pessoa);
    }
}
