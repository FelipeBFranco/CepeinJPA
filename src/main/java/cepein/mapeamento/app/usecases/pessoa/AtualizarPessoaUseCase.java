package cepein.mapeamento.app.usecases.pessoa;

import cepein.mapeamento.acore.domain.models.Pessoa;
import cepein.mapeamento.app.gateways.PessoaGatway;

public class AtualizarPessoaUseCase {

    private PessoaGatway pessoaGatway;

    public AtualizarPessoaUseCase(PessoaGatway pessoaGatway){
        this.pessoaGatway = pessoaGatway;
    }
    public void atualizarPessoa(Pessoa pessoa){
        this.pessoaGatway.salvarPessoa(pessoa);
    }
}
