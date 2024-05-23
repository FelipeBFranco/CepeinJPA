package cepein.mapeamento.infra.persistence.jpa.repositories.pedido;

import cepein.mapeamento.infra.persistence.jpa.entities.pedido.JpaPedidoCommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoCommandRepository extends JpaRepository<JpaPedidoCommandEntity,Long> {
}
