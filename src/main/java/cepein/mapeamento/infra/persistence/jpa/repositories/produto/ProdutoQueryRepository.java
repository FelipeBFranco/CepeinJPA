package cepein.mapeamento.infra.persistence.jpa.repositories.produto;

import cepein.mapeamento.infra.persistence.jpa.entities.produto.JpaProdutoQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoQueryRepository extends JpaRepository<JpaProdutoQueryEntity, Long> {
}
