package cepein.mapeamento.infra.persistence.jpa.repositories;

import cepein.mapeamento.infra.persistence.jpa.entities.JpaPessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<JpaPessoaEntity,Long> {

    JpaPessoaEntity findByUuid(String uuid);
}
