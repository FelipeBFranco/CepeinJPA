package cepein.mapeamento.pessoa_produto.repository;

import cepein.mapeamento.pessoa_produto.PessoaProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaProdutoRepository extends JpaRepository<PessoaProduto, Long> {
}
