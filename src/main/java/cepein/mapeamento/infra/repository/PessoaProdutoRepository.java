package cepein.mapeamento.infra.repository;

import cepein.mapeamento.model.PessoaProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaProdutoRepository extends JpaRepository<PessoaProduto, Long> {
}
