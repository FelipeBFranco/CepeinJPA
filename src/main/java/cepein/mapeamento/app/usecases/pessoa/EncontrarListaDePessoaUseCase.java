package cepein.mapeamento.app.usecases.pessoa;

import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import cepein.mapeamento.app.gateways.PessoaGatway;
import cepein.mapeamento.utils.clean.application.usecase.UseCaseResponse;

import java.util.List;

public class EncontrarListaDePessoaUseCase implements UseCaseResponse<List<PessoaQuery>> {
    private final PessoaGatway pessoaGatway;

    public EncontrarListaDePessoaUseCase(PessoaGatway pessoaGatway){
        this.pessoaGatway = pessoaGatway;
    }
    @Override
    public List<PessoaQuery> execute(){
        return  this.pessoaGatway.encontrarTodasAsPessoa();
    }
}
