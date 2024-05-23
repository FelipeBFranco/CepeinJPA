package cepein.mapeamento.app.usecases.curso;


import cepein.mapeamento.acore.domain.models.curso.CursoQuery;
import cepein.mapeamento.app.gateways.CursoGateway;
import cepein.mapeamento.infra.adapters.http.forms.CursoForms;
import cepein.mapeamento.utils.clean.application.useCase.UseCaseRequest;

public class AtualizarCursoUseCase implements UseCaseRequest<CursoForms> {
    private final CursoGateway cursoGateway;
    private Long idCurso;

    public AtualizarCursoUseCase(CursoGateway cursoGateway){
        this.cursoGateway = cursoGateway;
    }

    @Override
    public void execute(CursoForms cursoForms) {
        this.idCurso = cursoForms.getId();
        CursoQuery cursoQuery = this.cursoGateway.buscar(this.idCurso);
        this.cursoGateway.salvar(cursoForms.converter(cursoQuery));

    }
}
