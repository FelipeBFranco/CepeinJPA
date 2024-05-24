package cepein.mapeamento.app.usecases.curso;


import cepein.mapeamento.acore.domain.models.curso.CursoQuery;
import cepein.mapeamento.app.gateways.CursoGateway;
import cepein.mapeamento.utils.clean.application.usecase.UseCaseResponse;

import java.util.List;

public class EncontrarListaCursoUseCase implements UseCaseResponse<List<CursoQuery>> {
    private final CursoGateway cursoGateway;

    public EncontrarListaCursoUseCase(CursoGateway cursoGateway){
        this.cursoGateway = cursoGateway;
    }
    @Override
    public List<CursoQuery> execute(){
        return this.cursoGateway.encontrarTodosOsCursos();
    }
}
