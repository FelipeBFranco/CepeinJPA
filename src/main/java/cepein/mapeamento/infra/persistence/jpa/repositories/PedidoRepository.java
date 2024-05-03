package cepein.mapeamento.infra.persistence.jpa.repositories;

import cepein.mapeamento.infra.persistence.jpa.entities.JpaPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<JpaPedidoEntity, Long> {
}
