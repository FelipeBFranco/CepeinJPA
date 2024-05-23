package cepein.mapeamento.infra.persistence.jpa.repositories.pessoa;

import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.JpaPessoaCommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaCommandRepository extends JpaRepository<JpaPessoaCommandEntity,Long> {
}
