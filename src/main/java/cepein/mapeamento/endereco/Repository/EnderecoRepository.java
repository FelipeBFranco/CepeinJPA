package cepein.mapeamento.endereco.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cepein.mapeamento.endereco.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> { }
