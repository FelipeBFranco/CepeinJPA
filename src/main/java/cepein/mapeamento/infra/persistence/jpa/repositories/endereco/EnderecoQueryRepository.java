package cepein.mapeamento.infra.persistence.jpa.repositories.endereco;

import cepein.mapeamento.infra.persistence.jpa.entities.endereco.JpaEnderecoQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoQueryRepository extends JpaRepository<JpaEnderecoQueryEntity,Long> {
}
