package cepein.mapeamento.infra.repository;

import cepein.mapeamento.model.PessoaPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaPedidoRepository extends JpaRepository<PessoaPedido, Long> {

}
