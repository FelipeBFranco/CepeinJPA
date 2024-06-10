package cepein.mapeamento.app.usecases.curso;

import cepein.mapeamento.app.gateways.CursoGateway;
import cepein.mapeamento.utils.clean.application.usecase.UseCaseRequest;

public class DeletarCursoUseCase implements UseCaseRequest<Long> {
    private final CursoGateway cursoGateway;
    private Long idCurso;

    public DeletarCursoUseCase(CursoGateway cursoGateway){
        this.cursoGateway = cursoGateway;
    }
    @Override
    public void execute(Long id) {
        this.idCurso = id;
        this.verificarExistenciaCurso();
        this.cursoGateway.deletar(id);
    }
    private void verificarExistenciaCurso(){
        this.cursoGateway.buscar(idCurso);
    }
}
