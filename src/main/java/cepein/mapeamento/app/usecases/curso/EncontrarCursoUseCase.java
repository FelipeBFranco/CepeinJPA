package cepein.mapeamento.app.usecases.curso;


import cepein.mapeamento.acore.domain.models.curso.CursoQuery;
import cepein.mapeamento.app.gateways.CursoGateway;
import cepein.mapeamento.utils.clean.application.usecase.UseCase;

public class EncontrarCursoUseCase implements UseCase<Long,CursoQuery> {
    private final CursoGateway cursoGateway;

    public EncontrarCursoUseCase(CursoGateway cursoGateway){
        this.cursoGateway = cursoGateway;
    }

    @Override
    public CursoQuery execute(Long id) {
        return this.cursoGateway.buscar(id);
    }
}
