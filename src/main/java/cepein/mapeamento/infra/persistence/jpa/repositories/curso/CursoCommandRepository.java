package cepein.mapeamento.infra.persistence.jpa.repositories.curso;

import cepein.mapeamento.infra.persistence.jpa.entities.curso.JpaCursoCommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoCommandRepository extends JpaRepository<JpaCursoCommandEntity,Long> {
}
