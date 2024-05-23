package cepein.mapeamento.infra.persistence.jpa.repositories.curso;

import cepein.mapeamento.infra.persistence.jpa.entities.curso.JpaCursoQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoQueryRepository extends JpaRepository<JpaCursoQueryEntity, Long> {
}
