package cepein.mapeamento.app.usecases.curso;

import cepein.mapeamento.acore.domain.models.Curso;
import cepein.mapeamento.app.gateways.CursoGateway;

import java.util.List;

public class EncontrarListaCursoUseCase {
    private CursoGateway cursoGateway;

    public EncontrarListaCursoUseCase(CursoGateway cursoGateway){
        this.cursoGateway = cursoGateway;
    }
    public List<Curso> encontrarListaCurso(){
        return this.cursoGateway.encontrarTodosOsCursos();
    }
}
