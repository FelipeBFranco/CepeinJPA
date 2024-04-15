package cepein.mapeamento.infra.repository;

import cepein.mapeamento.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
