package cepein.mapeamento.app.usecases.pessoa;

import cepein.mapeamento.app.gateways.PessoaGatway;
import cepein.mapeamento.infra.adapters.http.forms.PessoaForms;
import cepein.mapeamento.utils.clean.application.useCase.UseCaseRequest;

public class CadastrarPessoaUseCase implements UseCaseRequest<PessoaForms> {
    private final PessoaGatway pessoaGatway;

    public CadastrarPessoaUseCase(PessoaGatway pessoaGatway){
        this.pessoaGatway = pessoaGatway;
    }
    @Override
    public void execute(PessoaForms pessoaForms ){
        this.pessoaGatway.salvar(pessoaForms.converter());
    }
}
