package cepein.mapeamento.infra.repository;

import cepein.mapeamento.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

    Pessoa findByUuid(String uuid);
}
