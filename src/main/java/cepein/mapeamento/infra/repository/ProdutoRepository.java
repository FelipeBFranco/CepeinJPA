package cepein.mapeamento.infra.repository;

import cepein.mapeamento.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
