package cepein.mapeamento.app.gateways;

import cepein.mapeamento.acore.domain.models.Curso;

import java.util.List;

public interface CursoGateway {

    Curso encontrarCursoPorId(Long id);
    List<Curso> encontrarTodosOsCursos();
    void salvarCurso(Curso curso);
    void deletarCursoPorId(Long id);
}
