package cepein.mapeamento.pessoa.repository;

import cepein.mapeamento.pessoa.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

    Pessoa findByUuid(String uuid);
}
