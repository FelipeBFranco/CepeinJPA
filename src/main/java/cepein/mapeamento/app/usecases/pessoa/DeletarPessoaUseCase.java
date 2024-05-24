package cepein.mapeamento.app.usecases.pessoa;

import cepein.mapeamento.app.gateways.PessoaGatway;
import cepein.mapeamento.app.services.pessoa.VerificarExistenciaPessoa;
import cepein.mapeamento.utils.clean.application.usecase.UseCaseRequest;

public class DeletarPessoaUseCase implements UseCaseRequest<Long> {
    private final PessoaGatway pessoaGatway;

    public DeletarPessoaUseCase(PessoaGatway pessoaGatway){
        this.pessoaGatway = pessoaGatway;
    }
    @Override
    public void execute(Long id){

        VerificarExistenciaPessoa verificarExistenciaPessoa = new VerificarExistenciaPessoa(this.pessoaGatway);

        verificarExistenciaPessoa.verificar(id);

        this.pessoaGatway.deletar(id);
    }
}
