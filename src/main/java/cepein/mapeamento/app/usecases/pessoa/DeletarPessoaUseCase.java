package cepein.mapeamento.app.usecases.pessoa;

import cepein.mapeamento.app.gateways.PessoaGatway;

public class DeletarPessoaUseCase {
    private PessoaGatway pessoaGatway;

    public DeletarPessoaUseCase(PessoaGatway pessoaGatway){
        this.pessoaGatway = pessoaGatway;
    }
    public void deletarPorId(Long id){
        this.pessoaGatway.deletarPessoaPorId(id);
    }
}
