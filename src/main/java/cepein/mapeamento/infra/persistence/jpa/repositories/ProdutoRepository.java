package cepein.mapeamento.infra.persistence.jpa.repositories;

import cepein.mapeamento.infra.persistence.jpa.entities.JpaProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<JpaProdutoEntity, Long> {
}
