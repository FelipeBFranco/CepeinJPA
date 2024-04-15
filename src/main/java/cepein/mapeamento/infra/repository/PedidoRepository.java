package cepein.mapeamento.infra.repository;

import cepein.mapeamento.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
