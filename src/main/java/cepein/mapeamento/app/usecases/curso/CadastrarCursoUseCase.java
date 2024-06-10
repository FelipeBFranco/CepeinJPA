package cepein.mapeamento.app.usecases.curso;


import cepein.mapeamento.app.gateways.CursoGateway;
import cepein.mapeamento.infra.adapters.http.forms.CursoForms;
import cepein.mapeamento.utils.clean.application.useCase.UseCase;
import cepein.mapeamento.utils.clean.application.useCase.UseCaseRequest;

public class CadastrarCursoUseCase implements UseCaseRequest<CursoForms> {
    private final CursoGateway cursoGateway;

    public CadastrarCursoUseCase(CursoGateway cursoGateway){
        this.cursoGateway = cursoGateway;
    }
    @Override
    public void execute(CursoForms cursoForms){
        this.cursoGateway.salvar(cursoForms.converter());

    }
}
