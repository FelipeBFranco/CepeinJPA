package cepein.mapeamento.app.usecases.curso;

import cepein.mapeamento.acore.domain.models.Curso;
import cepein.mapeamento.app.gateways.CursoGateway;

public class CadastrarCursoUseCase {
    private CursoGateway cursoGateway;

    public CadastrarCursoUseCase(CursoGateway cursoGateway){
        this.cursoGateway = cursoGateway;
    }
    public void criarCurso(Curso curso){
        this.cursoGateway.salvarCurso(curso);
    }
}
