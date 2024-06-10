package cepein.mapeamento.infra.persistence.jpa.repositories.pessoa;

import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.JpaPessoaQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaQueryRepository extends JpaRepository<JpaPessoaQueryEntity,Long> {

    Optional<JpaPessoaQueryEntity> findByUuid(String uuid);
}
