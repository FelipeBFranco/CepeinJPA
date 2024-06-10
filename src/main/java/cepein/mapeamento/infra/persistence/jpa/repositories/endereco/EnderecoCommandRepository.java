package cepein.mapeamento.infra.persistence.jpa.repositories.endereco;

import cepein.mapeamento.infra.persistence.jpa.entities.endereco.JpaEnderecoCommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoCommandRepository extends JpaRepository<JpaEnderecoCommandEntity, Long> {
}
