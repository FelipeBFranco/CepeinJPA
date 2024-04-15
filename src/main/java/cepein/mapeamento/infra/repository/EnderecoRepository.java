package cepein.mapeamento.infra.repository;

import cepein.mapeamento.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco,Long> {
}
