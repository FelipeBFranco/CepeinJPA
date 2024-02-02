package cepein.mapeamento.endereco.repository;

import cepein.mapeamento.endereco.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco,Long> {
}
