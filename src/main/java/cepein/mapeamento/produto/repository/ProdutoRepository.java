package cepein.mapeamento.produto.repository;

import cepein.mapeamento.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
