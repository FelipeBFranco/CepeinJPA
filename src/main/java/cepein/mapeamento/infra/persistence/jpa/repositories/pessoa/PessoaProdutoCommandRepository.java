package cepein.mapeamento.infra.persistence.jpa.repositories.pessoa;

import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaProduto.JpaPessoaProdutoCommandEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaProduto.JpaPessoaProdutoIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaProdutoCommandRepository extends JpaRepository< JpaPessoaProdutoCommandEntity, JpaPessoaProdutoIdEntity> {

}
