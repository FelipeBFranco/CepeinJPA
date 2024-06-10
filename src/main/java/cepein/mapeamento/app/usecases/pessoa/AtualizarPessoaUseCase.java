package cepein.mapeamento.app.usecases.pessoa;

import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import cepein.mapeamento.app.gateways.PessoaGatway;
import cepein.mapeamento.infra.adapters.http.forms.PessoaForms;
import cepein.mapeamento.utils.clean.application.usecase.UseCaseRequest;

public class AtualizarPessoaUseCase implements UseCaseRequest<PessoaForms> {

    private final PessoaGatway pessoaGatway;

    public AtualizarPessoaUseCase(PessoaGatway pessoaGatway){
        this.pessoaGatway = pessoaGatway;
    }
    @Override
    public void execute(PessoaForms pessoaForms ){
        PessoaQuery pessoaQuery = this.pessoaGatway.buscar(pessoaForms.getId());
        this.pessoaGatway.salvar(pessoaForms.converter(pessoaQuery));
    }
}
