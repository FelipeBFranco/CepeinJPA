package cepein.mapeamento.pedido.repository;

import cepein.mapeamento.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
