package cepein.mapeamento.app.usecases.curso;

import cepein.mapeamento.acore.domain.models.Curso;
import cepein.mapeamento.app.gateways.CursoGateway;

public class AtualizarCursoUseCase {
    private CursoGateway cursoGateway;

    public AtualizarCursoUseCase(CursoGateway cursoGateway){
        this.cursoGateway = cursoGateway;
    }
    public void atualizarCurso(Curso curso){
        this.cursoGateway.salvarCurso(curso);
    }
}
