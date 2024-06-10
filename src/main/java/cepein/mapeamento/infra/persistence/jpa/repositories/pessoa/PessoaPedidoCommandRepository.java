package cepein.mapeamento.infra.persistence.jpa.repositories.pessoa;

import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaPedido.JpaPessoaPedidoCommandEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaPedido.JpaPessoaPedidoIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaPedidoCommandRepository extends JpaRepository<JpaPessoaPedidoCommandEntity, JpaPessoaPedidoIdEntity> {
}
