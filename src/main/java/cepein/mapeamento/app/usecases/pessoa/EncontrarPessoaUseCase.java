package cepein.mapeamento.app.usecases.pessoa;

import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import cepein.mapeamento.app.gateways.PessoaGatway;
import cepein.mapeamento.utils.clean.application.usecase.UseCase;

public class EncontrarPessoaUseCase implements UseCase<Long,PessoaQuery> {
    private final PessoaGatway pessoaGatway;
    public EncontrarPessoaUseCase(PessoaGatway pessoaGatway){
        this.pessoaGatway = pessoaGatway;
    }
    @Override
    public PessoaQuery execute(Long id){
        return  this.pessoaGatway.buscar(id);
    }
}
