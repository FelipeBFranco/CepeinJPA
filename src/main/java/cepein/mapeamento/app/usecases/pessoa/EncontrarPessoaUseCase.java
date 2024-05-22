package cepein.mapeamento.app.usecases.pessoa;

import cepein.mapeamento.acore.domain.models.Pessoa;
import cepein.mapeamento.app.gateways.PessoaGatway;

public class EncontrarPessoaUseCase {
    private PessoaGatway pessoaGatway;
    public EncontrarPessoaUseCase(PessoaGatway pessoaGatway){
        this.pessoaGatway = pessoaGatway;
    }
    public Pessoa encontrarPessoa(Long id){
        return  this.pessoaGatway.encontrarPessoaPorId(id);
    }
}
