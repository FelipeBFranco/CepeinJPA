package cepein.mapeamento.infra.persistence.jpa.repositories;

import cepein.mapeamento.infra.persistence.jpa.entities.JpaCursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<JpaCursoEntity, Long> {
}
