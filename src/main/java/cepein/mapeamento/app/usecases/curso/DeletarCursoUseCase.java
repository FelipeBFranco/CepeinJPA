package cepein.mapeamento.app.usecases.curso;

import cepein.mapeamento.app.gateways.CursoGateway;

public class DeletarCursoUseCase {
    private CursoGateway cursoGateway;

    public DeletarCursoUseCase(CursoGateway cursoGateway){
        this.cursoGateway = cursoGateway;
    }
    public void deletarCurso(Long id){
        this.cursoGateway.deletarCursoPorId(id);
    }
}
