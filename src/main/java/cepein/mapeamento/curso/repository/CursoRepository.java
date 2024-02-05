package cepein.mapeamento.curso.repository;

import cepein.mapeamento.curso.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
