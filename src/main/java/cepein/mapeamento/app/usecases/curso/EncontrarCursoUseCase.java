package cepein.mapeamento.app.usecases.curso;

import cepein.mapeamento.acore.domain.models.Curso;
import cepein.mapeamento.app.gateways.CursoGateway;

public class EncontrarCursoUseCase {
    private CursoGateway cursoGateway;

    public EncontrarCursoUseCase(CursoGateway cursoGateway){
        this.cursoGateway = cursoGateway;
    }
    public Curso encontrarCurso(Long id){
        return this.cursoGateway.encontrarCursoPorId(id);
    }
}
