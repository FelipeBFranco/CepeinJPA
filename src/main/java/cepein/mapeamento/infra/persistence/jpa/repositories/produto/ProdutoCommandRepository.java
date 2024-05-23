package cepein.mapeamento.infra.persistence.jpa.repositories.produto;

import cepein.mapeamento.infra.persistence.jpa.entities.produto.JpaProdutoCommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoCommandRepository extends JpaRepository<JpaProdutoCommandEntity,Long> {
}
